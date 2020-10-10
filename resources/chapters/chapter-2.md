# Parameterized queries

Looking at this query:

    [:find ?title
     :where
     [?p :person/name "Sylvester Stallone"]
     [?m :movie/cast ?p]
     [?m :movie/title ?title]]

It would be great if we could reuse this query to find movie
titles for any actor and not just for "Sylvester Stallone". This is
possible with an `:in` clause, which provides the query with input
parameters, much in the same way that function or method arguments does
in your programming language.

Here's that query with an input parameter for the actor:

    [:find ?title
     :in $ ?name
     :where
     [?p :person/name ?name]
     [?m :movie/cast ?p]
     [?m :movie/title ?title]]

This query takes two arguments: `$` is the database itself (implicit,
if no `:in` clause is specified) and `?name` which presumably will be
the name of some actor.

The above query is executed like `(q query db "Sylvester Stallone")`,
where `query` is the query we just saw, and `db` is a database value.
You can have any number of inputs to a query.

## A quick aside

Hold on. Where does that `$` get used? In query, each of these data
patterns is actually a **5 tuple**, of the form:

    [<database> <entity-id> <attribute> <value> <transaction-id>]

It's just that the `database` part is implicit, much like the first
parameter in the `:in` clause. This query is functionally identical
to the previous one:

    [:find ?title
     :in $ ?name
     :where
     [$ ?p :person/name ?name]
     [$ ?m :movie/cast ?p]
     [$ ?m :movie/title ?title]]

## Relations

Relations - a set of tuples - are the most interesting and powerful of
input types, since you can join external relations with the datoms in
your database.

As a simple example, let's consider a relation with tuples `[movie-title box-office-earnings]`:

    [
     ...
     ["Die Hard" 140700000]
     ["Alien" 104931801]
     ["Lethal Weapon" 120207127]
     ["Commando" 57491000]
     ...
    ]

Let's use this data and the data in our database to find
box office earnings for a particular director:

    [:find ?title ?box-office
     :in $ ?director [[?title ?box-office]]
     :where
     [?p :person/name ?director]
     [?m :movie/director ?p]
     [?m :movie/title ?title]]

Note that the `?box-office` pattern variable does not
appear in any of the data patterns in the `:where` clause.
