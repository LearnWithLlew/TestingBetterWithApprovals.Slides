# Chatbot conversations

1. [[hi]](#scenario-1)
1. [[hi, hi]](#scenario-2)
1. [[hi, hi, hi]](#scenario-3)
1. [[hi, pay bill]](#scenario-4)
1. [[hi, pay bill, Yes, I'm a customer]](#scenario-5)
1. [[hi, pay bill, no]](#scenario-6)
1. [[hi, talk to an operator, Yes, I'm a customer]](#scenario-7)
1. [[oh hi there, how are you doing]](#scenario-8)
1. [[pay bill]](#scenario-9)
1. [[pay bill, I like swimming]](#scenario-10)
1. [[pay bill, I like coffee, I like tea, no]](#scenario-11)
1. [[walk my dog, Yes, I'm a customer]](#scenario-12)
1. [[talk to an operator]](#scenario-13)
1. [[pay bill, Yes, I'm a customer],[pay bill]](#scenario-14)
1. [[pay bill, No, I'm not],[pay bill]](#scenario-15)


### Scenario 1
#### [hi]
```
[Customer]: hi
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: What would you like to do today?

```
---

### Scenario 2
#### [hi, hi]
```
[Customer]: hi
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: What would you like to do today?
[Customer]: hi
[     Bot]: Hmmm, tell me a little more so I can help you.
What would you like to do today?

```
---

### Scenario 3
#### [hi, hi, hi]
```
[Customer]: hi
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: What would you like to do today?
[Customer]: hi
[     Bot]: Hmmm, tell me a little more so I can help you.
What would you like to do today?
[Customer]: hi
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not

```
---

### Scenario 4
#### [hi, pay bill]
```
[Customer]: hi
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: What would you like to do today?
[Customer]: pay bill
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not

```
---

### Scenario 5
#### [hi, pay bill, Yes, I'm a customer]
```
[Customer]: hi
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: What would you like to do today?
[Customer]: pay bill
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: Yes, I'm a customer
[     Bot]: transfers to 'self_service'

```
---

### Scenario 6
#### [hi, pay bill, no]
```
[Customer]: hi
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: What would you like to do today?
[Customer]: pay bill
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: no
[     Bot]: transfers to 'operator'

```
---

### Scenario 7
#### [hi, talk to an operator, Yes, I'm a customer]
```
[Customer]: hi
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: What would you like to do today?
[Customer]: talk to an operator
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: Yes, I'm a customer
[     Bot]: transfers to 'operator'

```
---

### Scenario 8
#### [oh hi there, how are you doing]
```
[Customer]: oh hi there, how are you doing
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not

```
---

### Scenario 9
#### [pay bill]
```
[Customer]: pay bill
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not

```
---

### Scenario 10
#### [pay bill, I like swimming]
```
[Customer]: pay bill
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: I like swimming
[     Bot]: I'd be happy to help you with this, But first, I need to know: are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not

```
---

### Scenario 11
#### [pay bill, I like coffee, I like tea, no]
```
[Customer]: pay bill
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: I like coffee
[     Bot]: I'd be happy to help you with this, But first, I need to know: are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: I like tea
[     Bot]: I'd be happy to help you with this, But first, I need to know: are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: no
[     Bot]: transfers to 'operator'

```
---

### Scenario 12
#### [walk my dog, Yes, I'm a customer]
```
[Customer]: walk my dog
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: Yes, I'm a customer
[     Bot]: transfers to 'operator'

```
---

### Scenario 13
#### [talk to an operator]
```
[Customer]: talk to an operator
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not

```
---

### Scenario 14
#### [pay bill, Yes, I'm a customer], [pay bill]
```
***** Conversation 1 *****
[Customer]: pay bill
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: Yes, I'm a customer
[     Bot]: transfers to 'self_service'

***** Conversation 2 *****
[Customer]: pay bill
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: Let me try to help you.
[     Bot]: transfers to 'self_service'

```
---

### Scenario 15
#### [pay bill, No, I'm not], [pay bill]
```
***** Conversation 1 *****
[Customer]: pay bill
[     Bot]: Hi there! I'm your virtual assistant.
[     Bot]: Let me try to help you.
[     Bot]: Are you a customer?
[        ]:   1) Yes, I'm a customer
[        ]:   2) No, I'm not
[Customer]: No, I'm not
[     Bot]: transfers to 'operator'

***** Conversation 2 *****
[Customer]: pay bill
[     Bot]: Let me try to help you.
[     Bot]: transfers to 'operator'

```
---

