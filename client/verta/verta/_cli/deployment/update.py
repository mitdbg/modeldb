# -*- coding: utf-8 -*-

import click
import json

from .deployment import deployment
from ... import Client
from ...deployment.update._strategies import DirectUpdateStrategy, CanaryUpdateStrategy
from ...deployment.update.rules import _UpdateRule


@deployment.group()
def update():
    pass

@update.command(name="endpoint")
@click.argument("path", nargs=1, required=True)
@click.option("--run-id", "-r", help="Experiment Run to deploy.")
@click.option("--strategy", "-s", type=click.Choice(['direct', 'canary'], case_sensitive=False), help="Strategy to use to roll out new deployment.")
@click.option("--canary-rule", "-c", multiple=True, help="Rule to use for canary deployment. Can only be used alongside --strategy=canary")
@click.option("--canary-interval", "-i", type=click.IntRange(min=0), help="Rollout interval, in seconds. Can only be used alongside --strategy=canary")
@click.option("--canary-step", type=click.FloatRange(min=0.0, max=1.0), help="Rollout interval, in seconds. Can only be used alongside --strategy=canary")
@click.option("--workspace", "-w", help="Workspace to use.")
# TODO: more options
def update_endpoint(path, run_id, strategy, canary_rule, canary_interval, canary_step, workspace):
    """List all endpoints available.
    """
    if canary_step == 0.0:
        raise click.BadParameter("--canary-step must be positive.")

    if canary_interval == 0:
        raise click.BadParameter("--canary-interval must be positive.")

    if strategy != "canary" and (canary_rule or canary_interval or canary_step):
        raise click.BadParameter("--canary-rule, --canary-interval, and --canary-step can only be used alongside --strategy=canary")

    if strategy == "canary" and (not canary_rule or not canary_interval or not canary_step):
        raise click.BadParameter("--canary-rule, --canary-interval, and --canary-step must be provided alongside --strategy=canary")

    client = Client()

    try:
        endpoint = client.get_endpoint(path=path, workspace=workspace)
    except ValueError:
        raise click.BadParameter("endpoint with path {} not found".format(path))

    try:
        run = client.get_experiment_run(id=run_id)
    except ValueError:
        raise click.BadParameter("experiment run with id {} not found".format(run_id))

    if strategy == 'direct':
        endpoint.update(run, DirectUpdateStrategy())
    else:
        # strategy is canary
        strategy_obj = CanaryUpdateStrategy(canary_interval, canary_step)
        for rule in canary_rule:
            strategy_obj.add_rule(_UpdateRule._from_dict(json.loads(rule)))
        endpoint.update(run, strategy_obj)
