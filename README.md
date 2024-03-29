# firstBlockchain

Blockchain is one of the upcoming technologies of the century. It creates a decentralized system where its users manage their own goods, without the need for a third party intermediary. It is also very secure and nearly impossible to break into or tamper with data due to its data structure design. It has applications in all industries such as robotics, banking, the Internet of Things, investing, real estate, supply chain management, and more. Below I have a rudimentary drawing (courtesy of Microsoft Paint!) of the blockchain architecture. 

![My attempt at trying to understand blockchain](https://i.imgur.com/iqIXN1z.png)

Blockchain works by basically storing multiple ledgers into a chain. Each ledger has 3 main items (This is an oversimplification, but this is what I have implemented in my program.). The data of the program describes what a user wants to put in. It can be device battery status, bank account number, journal entry, basically anything that one can think of. Then, each ledger stores a computed hash. It uses the timestamp of the current time the chain was created, along with a SHA-256 encoded digit and several other random variables to store the hash. A given block also stores the hash of the previous block, denoted as the prevHash variable in the following program. 

Every time a new block is added, the blockchain needs to perform some calculations in order to validate if a transaction is secure or tampered. This is called "mining" and it helps to prevent fraudulent transactions. It uses a random calculation method in order to find a target hatch. All nodes have a copy of the blockchain and a discrepency would be detected by all the nodes and the "tamper" will not be accepted. Since each mining step takes a long time to verify and all ledgers need to verify a transaction, it is hard to edit the blockchain and makes it a secure data structure for many future applications. 

This particular program stores data through JSON. 
