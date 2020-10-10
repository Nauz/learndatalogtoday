# Transactions

A datom, as described earlier, is the 4-tuple `[eid attr val tx]`. So far, we have only asked questions about values and/or entity-ids. It's important to remember that it's also possible to run queries to find information about transactions, such as:

* When was a fact asserted?
* When was a fact retracted?
* Which facts were part of a transaction?

The transaction entity is the fourth element in the datom vector. The only attribute associated with a transaction (by default) is `:db/txInstant` which is the instant in time when the transaction was committed to the database.

Here's how we use the fourth element to find the time that "James Cameron" was set as the name for that person entity:

    [:find ?timestamp
     :where
     [?p :person/name "James Cameron" ?tx]
     [?tx :db/txInstant ?timestamp]]
     
