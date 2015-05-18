#Project Description

Implement a simple pipelined job service (further PJS). 

Client writes a very simple application form and put it to PJS. PJS consists of several individual processes that handles application in sequential order:

 - Verifier is responsible for verification of the application form format. It might be very simple handling mechanism i.e. check the matching user by his individual identity number (ID) in database (you can use simple text file).
 - Acknowledger receives verified application form and assigns it an unique identificator (autoincrement id or uuid).
 - HR receives application form from Acknowledger with assigned unique number, put it to the database and generate notification to the original sender (client) to message that he receives application form. Response have to be piggybacked with unique number assigned by Acknowledger.
 
There should be no intermediaries involved in the process of forwarding a job between servers: i.e. each server should pass the application directly to the next server. Stay focus on the system architecture and the process of passing applications around. Don’t implement complex data structures to represent application. It can be as simple as tiny json message.

How to implement this task? I don't know how to return response from HR part to client directly, because client initially starts calling from Verifier.

# How to run

1. Start orb service: *orbd -ORBInitialPort 1050*
2. Start server instances: ServerV, ServerAck, ServerHR
3. Run client: Client

# Report

## Design
We've separated each process into separate idl file. Then we've generated java source file using standard built-into JDK tool:

*idlj -fall IDL_FILE.idl* 

We've implemented each server separately, and put them into _servers_ package. 

Here is a simple scheme how the PJS works:

```
Client			Verifier			Acknowledger			HumanResources 
  |	call verify(..)	| 					 |					 	  |
  |---------------->|   call ack(..)	 |						  |	
  |					|------------------->|	  call save(..)		  |
  |					|				 	 |----------------------->|
  | 				|				 	 |	return response		  |	
  | 				|  return response	 |<-----------------------|	
  | 				|<-------------------|						  |	
  |     return		|					 |						  |	
  |    response		|					 |						  |	
  |<----------------|					 |						  |	
  | 				|				     |						  |	
```

## Evaluation
Modularity evaluation is very high, because we separated each process into separate _idl_ file and thus they don't depend on each other.  

