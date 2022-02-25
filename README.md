# Bananazura Spring Helper

This project is created for general solutions for spring framework. 

# Initial Version

This version include exception handling, logging mechanism, and handling rest controller fail scenarios with aop facilities.

# Exception Handling

The rest_aop_helper project provides exception handling with Aspect-Oriented-Programming manner. When Bananazura Aspects are enbaled, developer can be careless about exception handling. That is why, aop catches exceptions automatically, and it can create log for error. Also, it can be create a fail scenario for rest services. Therefore, developer can focus main and business concerns without thinking about cross-cutting concerns.  

# Logging Mechanism

Aspect-Oriented Programming provides to seperate cross-cutting concerns to main and business concerns. One of the cross-cutting concern is logging, so clean code require to remove cross-cutting concerns on code. You can do with AOP, luckily, Bananazura Spring Helper does for you.

Bananazura Spring Helper automatically logs all actions as info and error which is at on "base package" and "sub packages of base package". 

# Handling Rest Controller

Bananazura Spring Helper wraps all exceptions as BananazuraException instead of MethodArgumentNotValidException. All exceptions are thrown to rest controller classes. On the other hand, Bananazura Spring Helper catches all exceptions and creates a fail scenario and returns a suitable response.

# Please check rest_aop_helper and common README.md files for more detail and how to use.
Common README.md: 
https://github.com/berkayelken/spring_helper/blob/master/common/README.md
Rest Aop Helper README.md:
https://github.com/berkayelken/spring_helper/blob/master/rest_aop_helper/README.md
