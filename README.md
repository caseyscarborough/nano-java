# raiblocks-java

Java API for the RaiBlocks RPC protocol. Currently under active development.

## Usage

```java
// defaults to http://localhost:7076
RaiBlocksClient client = new RaiBlocksClient();

// or specify the host
RaiBlocksClient client = new RaiBlocksClient("http://123.45.67.8:7076");

// Check account balance
AccountBalance balance = client.getAccountBalance("xrb_3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3");
```

## Implemented methods

- [ ] Accounts
  - [x] Account balance
  - [x] Account block count
  - [x] Account create
  - [ ] Account get
  - [ ] Account history
  - [ ] Account information
  - [ ] Account list
  - [ ] Account move
  - [x] Account public key
  - [ ] Account remove
  - [ ] Account representative set
  - [ ] Account representative
  - [ ] Account weight
  - [ ] Accounts balances
  - [ ] Accounts create
  - [ ] Accounts frontiers
  - [ ] Accounts pending
  - [ ] Validate account number checksum
- [ ] Blocks
  - [ ] Block account
  - [ ] Block count by type
  - [ ] Block count
  - [ ] Chain
  - [ ] Offline signing (create block)
  - [ ] Process block
  - [ ] Retrieve block
  - [ ] Retrieve multiple blocks with additional info
  - [ ] Retrieve multiple blocks
- [ ] Bootstrap
  - [ ] Bootstrap
  - [ ] Multi-connection bootstrap
- [ ] Conversion
  - [ ] Krai from raw
  - [ ] Krai to raw
  - [ ] Mrai from raw
  - [ ] Mrai to raw
  - [ ] Rai from raw
  - [ ] Rai to raw
- [ ] Delegators
  - [ ] Delegators
  - [ ] Delegators count
- [ ] Frontiers
  - [ ] Frontiers
  - [ ] Frontier count
- [ ] Keys
  - [ ] Deterministic key
  - [ ] Key create
  - [ ] Key expand
- [ ] Ledger
  - [ ] History
  - [ ] Ledger
  - [ ] Successors
- [ ] Network
  - [ ] Available supply
  - [ ] Keepalive
  - [ ] Republish
- [ ] Node
  - [ ] Retrieve node versions
  - [ ] Stop node
- [ ] Payments
  - [ ] Payment begin
  - [ ] Payment end
  - [ ] Payment init
  - [ ] Payment wait
- [ ] Peers
  - [ ] Add work peer
  - [ ] Clear work peers
  - [ ] Retrieve online peers
  - [ ] Retrieve work peers
- [ ] Pending
  - [ ] Pending
  - [ ] Pending exists
  - [ ] Search pending
  - [ ] Search pending for all wallets
- [ ] Proof of Work
  - [ ] Work cancel
  - [ ] Work generate
  - [ ] Work get
  - [ ] Work set
  - [ ] Work validate
- [ ] Receiving
  - [ ] Receive
  - [ ] Receive minimum
  - [ ] Receive minimum set
- [ ] Representatives
  - [ ] Representatives
  - [ ] Wallet representative
  - [ ] Wallet representative set
- [ ] Sending
  - [ ] Send
- [ ] Unchecked blocks
  - [ ] Clear unchecked blocks
  - [ ] Retrieve unchecked block
  - [ ] Unchecked blocks with database keys
  - [ ] Unchecked blocks
- [ ] Wallet
  - [ ] Wallet accounts balances
  - [ ] Wallet add key
  - [ ] Wallet change password
  - [ ] Wallet change seed
  - [ ] Wallet contains
  - [ ] Wallet create
  - [ ] Wallet destroy
  - [ ] Wallet export
  - [ ] Wallet frontiers
  - [ ] Wallet locked check
  - [ ] Wallet password enter (unlock wallet)
  - [ ] Wallet pending
  - [ ] Wallet representative set
  - [ ] Wallet representative
  - [ ] Wallet republish
  - [ ] Wallet total balance
  - [ ] Wallet valid password
  - [ ] Wallet work get
- [ ] RPC callback
