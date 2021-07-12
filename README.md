# Thork
The Orchestrator

Thork is a fully non blocking ochestrator capable of orchestrating api,command,repository actions to define a end-to-end sagas.
Thork utilizes Json inheritance for construction of orchetration config

Sample Thork config


```json
{
  "id": "ORDER-ORCHESTRATOR-PATH",
  "level": 0,
  "num" : 0,
  "type": "ALL",
  "action": {
    "id": "INITIALIZE-ORCHESTRATION",
    "type" : "api"
  },
  "decisions": [
    {
      "id": "node",
      "level": 1,
      "num": 1,
      "type": "ALL",
      "action": {
        "id": "ORDER-ACTION-1",
        "type" : "api"
      },
      "decisions": [
        {
          "id": "node",
          "level": 2,
          "num": 1,
          "type": "ALL",
          "action": {
            "id": "VALIDATE-BUY-ORDER",
            "type" : "api"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 2,
          "type": "ALL",
          "action": {
            "id": "PREPARE-BUY-ORDER",
            "type" : "api"
          }
        }
      ]
    },
    {
      "id": "node",
      "level": 1,
      "num": 2,
      "type": "ALL",
      "action": {
        "id": "ORDER-ACTION-2",
        "type" : "api"
      },
      "decisions": [
        {
          "id": "node",
          "level": 2,
          "num": 1,
          "type": "ALL",
          "action": {
            "id": "CHECK-BUY-ORDER-EXISTS-IN-DB",
            "type" : "api"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 2,
          "type": "ALL",
          "action": {
            "id": "CHECK-BUY-ORDER-PRICE-RANGE",
            "type" : "api"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 3,
          "type": "ALL",
          "action": {
            "id": "CHECK-BUY-ORDER-QUANTITY",
            "type" : "api"
          }
        }
      ]
    },
    {
      "id": "node",
      "level": 1,
      "num": 3,
      "type": "ALL",
      "action": {
        "id": "ORDER-ACTION-3",
        "type" : "api"
      },
      "decisions": [
        {
          "id": "node",
          "level": 2,
          "num": 1,
          "type": "ALL",
          "action": {
            "id": "RESERVE-BUY-ORDER-QUANTITY",
            "type" : "api"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 2,
          "type": "ALL",
          "action": {
            "id": "RESERVE-BUY-ORDER-MONEY",
            "type" : "api"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 3,
          "type": "ALL",
          "action": {
            "id": "EXECUTE-BUY-ORDER-QUANTITY",
            "type" : "api"
          }
        }
      ]
    }
  ]
}
```
