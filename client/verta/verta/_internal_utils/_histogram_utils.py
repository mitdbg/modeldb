# -*- coding: utf-8 -*-

from ..external import six


def calculate_histogram(data):
    """
    Calculates a histogram for `data`.

    Parameters
    ----------
    data : pandas.Series
        Data to be binned.

    Returns
    -------
    histogram : dict

    """
    try:  # binary
        return calculate_binary_histogram(data)
    except HistogramError:
        pass

    try:  # discrete
        return calculate_discrete_histogram(data)
    except HistogramError:
        pass

    # continuous
    return calculate_float_histogram(data)

def calculate_binary_histogram(data):
    """
    Calculates a histogram for binary `data`.

    Parameters
    ----------
    data : pandas.Series
        Binary data to be binned.

    Returns
    -------
    histogram : dict

    Raises
    ------
    HistogramError
        If a binary histogram cannot be calculated from `data`.

    """
    values = data.values.tolist()

    zeros = 0
    ones = 0
    for value in values:
        if isinstance(value, bool):
            if value == False:
                zeros += 1
                continue
            elif value == True:
                ones += 1
                continue

        if isinstance(value, six.string_types):
            # handle bool-like strings
            if value.lower() == "false":
                zeros += 1
                continue
            elif value.lower() == "true":
                ones += 1
                continue

            # handle num-like strings (falls through to numeric case)
            try:
                value = float(value)
            except ValueError:
                pass

        if isinstance(value, (six.integer_types, float)):
            if value == 0:
                zeros += 1
                continue
            elif value == 1:
                ones += 1
                continue

        # unsupported value
        raise HistogramError("invalid binary value {}".format(value))

    return {
        'histogram': {
            'binary': {
                'count': [zeros, ones],
            },
        },
        'type': "binary",
    }

def calculate_discrete_histogram(data):
    """
    Calculates a histogram for discrete `data`.

    Parameters
    ----------
    data : pandas.Series of int
        Discrete data to be binned.

    Returns
    -------
    histogram : dict

    Raises
    ------
    HistogramError
        If a discrete histogram cannot be calculated from `data`.

    """
    value_counts = data.value_counts().sort_index()
    values = value_counts.index.tolist()
    counts = value_counts.values.tolist()

    # reject non-numbers
    try:
        values = list(map(float, values))
    except ValueError:
        raise HistogramError(
            "values must be castable to numbers"
        )

    # reject non-integral floats
    if not all(value.is_integer() for value in values):
        raise HistogramError(
            "values must be integers"
        )
    values = list(map(int, values))

    # heuristic: reject if too many values
    if len(values) > 10:
        raise HistogramError(
            "got {} possible discrete values but heuristic says the maximum is 10".format(len(values))
        )

    # heuristic: reject if counts don't seem high enough
    if value_counts.mean() < 10:  # `value_counts` instead of `counts` for mean() method
        raise HistogramError(
            "heuristic says that each discrete value should average at least 10 appearances"
        )

    return {
        'histogram': {
            'discrete': {
                'bucket_values': values,
                'count': counts,
            },
        },
        'type': "discrete",
    }

def calculate_float_histogram(data, num_bins=10):
    """
    Calculates a histogram for continuous `data`.

    Parameters
    ----------
    data : pandas.Series of float
        Continuous data to be binned.
    num_bins : int, default 10
        Number of bins to use.

    Returns
    -------
    histogram : dict

    """
    # calculate bin boundaries
    start, stop = min(data), max(data)
    space = (stop - start)/num_bins
    bin_boundaries = [start + space*i for i in range(num_bins+1)]

    # fit `data` into bins
    reference_counts = []
    for l, r in zip(bin_boundaries[:-1], bin_boundaries[1:]):
        count = len([datum for datum in data if l <= datum < r])
        reference_counts.append(count)

    return {
        'histogram': {
            'float': {
                'bucket_limits': bin_boundaries,
                'count': reference_counts,
            },
        },
        'type': "float",
    }


class HistogramError(TypeError):  # TODO: move to exceptions submodule
    pass
