Design considerations:

-Trivial methods were excluded from the class diagram.
-When a credit card is swiped, the Credit Card Scanner utility is used to gather the information which the Credit Card class contains.
-The Credit Card Scanner utility uses the Payment Processing Service utility to complete the transaction
-The "rawAmount" variable in the Transaction class holds the amount before any discounts are applied. The "finalAmount" variable is derived from the rawAmount and any discounts that were applied.
-A customer can be added into the system without any credit card or transaction associated with them.
-The Email class sends an email to a customer when the appropriate conditions are reached.  Each customer only has one email associated with them.
-An assumption is made that each no two customers will share the same credit card. That is to say, one customer can have multiple credit cards, but one credit card cannot be used by multiple people.  It can only be used by the designated card holder.
-Each customer is associated with zero or more transactions which can be viewed by the Stall Manager via the "displayTransactions()" method
-The Money variable "yearPurchaseTotal" located in the Customer class keeps track of how much a customer has spent in the calendar year. This is then used to figure out if the customer is eligible for Gold status or not.