# learndatalogtoday

An interactive Datalog tutorial

## Prerequisites

You will need [Leiningen](https://github.com/technomancy/leiningen) installed.

## Bootstrapping

    $ git clone git@github.com:Nauz/learndatalogtoday.git
    $ cd learndatalogtoday
    $ ./fetch-js-deps.sh
    $ lein clean && lein cljsbuild once

## Running the webapp

    lein ring server

A browser window should open, otherwise visit http://localhost:3000
