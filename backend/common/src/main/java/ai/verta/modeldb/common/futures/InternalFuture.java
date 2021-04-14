package ai.verta.modeldb.common.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class InternalFuture<T> {
  private CompletionStage<T> stage;

  private InternalFuture() {}

  public static <T> InternalFuture<Void> allOf(List<InternalFuture<T>> futures, Executor executor) {
    if (futures.isEmpty()) {
      return InternalFuture.completedInternalFuture(null);
    }
    return futures.stream()
        .reduce((x, y) -> x.thenCompose(unsued -> y, executor))
        .get()
        .thenRun(() -> {}, executor);
  }

  public static <T> InternalFuture<List<T>> sequence2(
      final List<InternalFuture<T>> futures, Executor executor) {
    final var promise = new CompletableFuture<List<T>>();
    final var values = new ArrayList<T>(futures.size());

    InternalFuture.allOf(futures, executor)
        .thenRun(
            () -> {
              try {
                for (final var future : futures) {
                  values.add(future.get());
                }
                promise.complete(values);
              } catch (Throwable t) {
                promise.completeExceptionally(t);
              }
            },
            executor);

    return InternalFuture.from(promise);
  }

  // Convert a list of futures to a future of a list
  public static <T> InternalFuture<List<T>> sequence(
      final List<InternalFuture<T>> futures, Executor executor) {
    final var promise = new CompletableFuture<List<T>>();
    final var values = new ArrayList<T>(futures.size());
    final CompletableFuture<T>[] futuresArray =
        futures.stream()
            .map(x -> x.toCompletionStage().toCompletableFuture())
            .collect(Collectors.toList())
            .toArray(new CompletableFuture[futures.size()]);

    CompletableFuture.allOf(futuresArray)
        .thenRunAsync(
            () -> {
              try {
                for (final var future : futuresArray) {
                  values.add(future.get());
                }
                promise.complete(values);
              } catch (Throwable t) {
                promise.completeExceptionally(t);
              }
            },
            executor);

    return InternalFuture.from(promise);
  }

  public static <R> InternalFuture<R> from(CompletionStage<R> other) {
    var ret = new InternalFuture<R>();
    ret.stage = other;
    return ret;
  }

  public static <R> InternalFuture<R> completedInternalFuture(R value) {
    var ret = new InternalFuture<R>();
    ret.stage = CompletableFuture.completedFuture(value);
    return ret;
  }

  public <U> InternalFuture<U> thenCompose(
      Function<? super T, InternalFuture<U>> fn, Executor executor) {
    return from(
        stage.thenComposeAsync(fn.andThen(internalFuture -> internalFuture.stage), executor));
  }

  public <U> InternalFuture<U> thenApply(Function<? super T, ? extends U> fn, Executor executor) {
    return from(stage.thenApplyAsync(fn, executor));
  }

  public InternalFuture<Void> thenAccept(Consumer<? super T> action, Executor executor) {
    return from(stage.thenAcceptAsync(action, executor));
  }

  public <U> InternalFuture<Void> thenRun(Runnable runnable, Executor executor) {
    return from(stage.thenRunAsync(runnable, executor));
  }

  public static <U> InternalFuture<Void> runAsync(Runnable runnable, Executor executor) {
    return from(CompletableFuture.runAsync(runnable, executor));
  }

  public static <U> InternalFuture<U> supplyAsync(Supplier<U> supplier, Executor executor) {
    return from(CompletableFuture.supplyAsync(supplier, executor));
  }

  public static <U> InternalFuture<U> failedStage(Throwable ex) {
    return from(CompletableFuture.failedFuture(ex));
  }

  public InternalFuture<T> whenComplete(
      BiConsumer<? super T, ? super Throwable> action, Executor executor) {
    return from(stage.whenCompleteAsync(action, executor));
  }

  public T get() throws ExecutionException, InterruptedException {
    return stage.toCompletableFuture().get();
  }

  public CompletionStage<T> toCompletionStage() {
    return stage;
  }
}
