
Recorded call to : Conversation Details API

# Request
| Method | URL |
| --- | --- |
| PUT | /api/v1.2/message/id/500 |

# Headers
```json
{
  "Authorization" : [ "Basic fake" ],
  "Cache-Control" : [ "no-cache" ]
}
```

# Body

```json
{
  "data" : [ {
    "displayNumber" : "500",
    "encodedCaseDisposition" : 27,
    "uuid" : "uuid-1",
    "createDate" : 0,
    "responsePortalLink" : "/portal/message/500",
    "primaryMessage" : {
      "conversationDisplayId" : "500",
      "network" : "twitter",
      "networkInstance" : "twitter",
      "receiveDate" : 0,
      "publishDate" : 0,
      "tags" : [ ],
      "private" : true
    }]
}
```




# Example Response

```json
{
    "messages" : [ {
      "conversationDisplayId" : "500",
      "network" : "twitter",
      "networkInstance" : "twitter",
      "receiveDate" : 0,
      "publishDate" : 0,
      "tags" : [ ],
      "private" : true
    } ],   
    "priority" : 4,
    "lswPermalink" : "/console/bot/500",
    "totalResponses" : 2,
    "hasResponse" : false,
    "status" : "IN_PROGRESS"
  } ]
}
```

