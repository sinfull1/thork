# Thork
The Orchestrator

Thork is a fully non blocking ochestrator capable of orchestrating api,command,repository actions to define an
end-to-end sagas execution

Thork utilizes Jackson Json inheritance for orchestration config defintiion

Sample Thork config


```json
{
  "id": "ORDER-ORCHESTRATOR-PATH",
  "level": 0,
  "num" : 0,
  "type": "ALL",
  "action": {
    "id": "INITIALIZE-ORCHESTRATION",
    "type" : "API"
  },
  "onFail": {
    "id": "ROLLBACK-INITIALIZE-ORCHESTRATION",
    "type" : "ON-FAIL"
  },
  "decisions": [
    {
      "id": "node",
      "level": 1,
      "num": 1,
      "type": "ALL",
      "action": {
        "id": "ORDER-ACTION-1",
        "type" : "API"
      },
      "onFail": {
        "id": "ROLLBACK-ORDER-ACTION-1",
        "type" : "ON-FAIL"
      },
      "decisions": [
        {
          "id": "node",
          "level": 2,
          "num": 1,
          "type": "ALL",
          "action": {
            "id": "VALIDATE-BUY-ORDER",
            "type" : "API"
          },
          "onFail": {
            "id": "ROLLBACK-VALIDATE-BUY-ORDER",
            "type" : "ON-FAIL"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 2,
          "type": "ALL",
          "action": {
            "id": "PREPARE-BUY-ORDER",
            "type" : "API"
          },
          "onFail": {
            "id": "ROLLBACK-PREPARE-BUY-ORDER",
            "type" : "ON-FAIL"
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
        "type" : "API"
      },
      "onFail": {
        "id": "ROLLBACK-ORDER-ACTION-2",
        "type" : "ON-FAIL"
      },
      "decisions": [
        {
          "id": "node",
          "level": 2,
          "num": 1,
          "type": "ALL",
          "action": {
            "id": "CHECK-BUY-ORDER-EXISTS-IN-DB",
            "type" : "API"
          },
          "onFail": {
            "id": "ROLLBACK-CHECK-BUY-ORDER-EXISTS-IN-DB",
            "type" : "ON-FAIL"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 2,
          "type": "ALL",
          "action": {
            "id": "CHECK-BUY-ORDER-PRICE-RANGE",
            "type" : "API"
          },
          "onFail": {
            "id": "ROLLBACK-CHECK-BUY-ORDER-PRICE-RANGE",
            "type" : "ON-FAIL"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 3,
          "type": "ALL",
          "action": {
            "id": "CHECK-BUY-ORDER-QUANTITY",
            "type" : "API"
          },
          "onFail": {
            "id": "ROLLBACK-CHECK-BUY-ORDER-QUANTITY",
            "type" : "ON-FAIL"
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
        "type" : "API"
      },
      "onFail": {
        "id": "ROLLBACK-ORDER-ACTION-3",
        "type" : "ON-FAIL"
      },
      "decisions": [
        {
          "id": "node",
          "level": 2,
          "num": 1,
          "type": "ALL",
          "action": {
            "id": "RESERVE-BUY-ORDER-QUANTITY",
            "type" : "API"
          },
          "onFail": {
            "id": "ROLLBACK-RESERVE-BUY-ORDER-QUANTITY",
            "type" : "ON-FAIL"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 2,
          "type": "ALL",
          "action": {
            "id": "RESERVE-BUY-ORDER-MONEY",
            "type" : "API"
          },
          "onFail": {
            "id": "ROLLBACK-RESERVE-BUY-ORDER-MONEY\"",
            "type" : "ON-FAIL"
          }
        },
        {
          "id": "node",
          "level": 2,
          "num": 3,
          "type": "ALL",
          "action": {
            "id": "EXECUTE-BUY-ORDER-QUANTITY",
            "type" : "API"
          },
          "onFail": {
            "id": "ROLLBACK-EXECUTE-BUY-ORDER-QUANTITY",
            "type" : "ON-FAIL"
          }
        }
      ]
    }
  ]
}
```
