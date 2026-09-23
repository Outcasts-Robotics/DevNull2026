{
  "startPoint": {
    "x": 60,
    "y": 8,
    "name": "intakeFlower",
    "locked": false,
    "headingDeg": 90
  },
  "lines": [
    {
      "id": "line-muc35kd5-rumyy5",
      "color": "#ffc516",
      "name": "scorePreload",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 60,
        "y": 36
      },
      "controlPoints": [],
      "heading": {
        "type": "constant",
        "startDeg": 90,
        "endDeg": 180,
        "degrees": 90
      }
    },
    {
      "id": "line-mudjpnj1-3r7li0",
      "color": "#5BBDCC",
      "name": "intakeGarden",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 8,
        "y": 8
      },
      "controlPoints": [
        {
          "x": 74.28733031674209,
          "y": 4.7526395173454
        }
      ],
      "heading": {
        "type": "constant",
        "reverse": false,
        "degrees": 180
      }
    },
    {
      "id": "line-mudinu63-lrs4g4",
      "color": "#C6A8A5",
      "name": "scoreGarden",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 55.213423831070884,
        "y": 110
      },
      "controlPoints": [
        {
          "x": 11.1447963800905,
          "y": 44.590497737556575
        },
        {
          "x": 87.10331825037707,
          "y": 11.077677224736037
        }
      ],
      "heading": {
        "type": "piecewise",
        "reverse": true,
        "degrees": 90,
        "piecewiseHeading": {
          "segments": [
            {
              "startProgress": 0,
              "endProgress": 0.5,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "startDeg": 180,
                "endDeg": 180
              }
            },
            {
              "startProgress": 0.5,
              "endProgress": 1,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "startDeg": 180,
                "endDeg": -90
              }
            }
          ]
        }
      }
    },
    {
      "id": "line-mudju8n4-3zvn84",
      "color": "#8BDC9A",
      "name": "IntakeFlower",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 60,
        "y": 130
      },
      "controlPoints": [],
      "heading": {
        "type": "piecewise",
        "reverse": false,
        "startDeg": -90,
        "endDeg": 90,
        "piecewiseHeading": {
          "segments": [
            {
              "startProgress": 0,
              "endProgress": 0.5,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "startDeg": -90,
                "endDeg": 90
              }
            },
            {
              "startProgress": 0.5,
              "endProgress": 1,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "startDeg": 90,
                "endDeg": 90
              }
            }
          ]
        }
      }
    },
    {
      "id": "line-mudipvgx-l5d8s3",
      "color": "#7D567B",
      "name": "scoreFlower",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 55,
        "y": 110
      },
      "controlPoints": [],
      "heading": {
        "type": "piecewise",
        "reverse": true,
        "startDeg": 0,
        "endDeg": 270,
        "piecewiseHeading": {
          "segments": [
            {
              "startProgress": 0,
              "endProgress": 0.1683366733466934,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "startDeg": 94,
                "endDeg": 90
              }
            },
            {
              "startProgress": 0.1683366733466934,
              "endProgress": 1,
              "interpolationType": "linear",
              "reversed": false,
              "continueFromPrevious": false,
              "parameters": {
                "startDeg": 90,
                "endDeg": -90
              }
            }
          ]
        }
      }
    },
    {
      "id": "line-mudiv18m-fwip5k",
      "color": "#77DBA5",
      "name": "park",
      "locked": false,
      "waitBeforeMs": 0,
      "waitAfterMs": 0,
      "waitBeforeName": "",
      "waitAfterName": "",
      "kind": "atomic",
      "endPoint": {
        "x": 8,
        "y": 110
      },
      "controlPoints": [],
      "heading": {
        "type": "linear",
        "reverse": true,
        "startDeg": -90,
        "endDeg": 180
      }
    }
  ],
  "shapes": [
    {
      "id": "triangle-1",
      "name": "Red Goal",
      "vertices": [
        {
          "x": 141.5,
          "y": 70
        },
        {
          "x": 141.5,
          "y": 141.5
        },
        {
          "x": 118.3,
          "y": 141.5
        },
        {
          "x": 135.5,
          "y": 118
        },
        {
          "x": 136.3,
          "y": 70.2
        }
      ],
      "color": "#dc2626",
      "fillColor": "#ff6b6b"
    },
    {
      "id": "triangle-2",
      "name": "Blue Goal",
      "vertices": [
        {
          "x": 6.2,
          "y": 116.9
        },
        {
          "x": 25,
          "y": 141.5
        },
        {
          "x": 0,
          "y": 141.5
        },
        {
          "x": 0,
          "y": 70
        },
        {
          "x": 6,
          "y": 70
        }
      ],
      "color": "#2563eb",
      "fillColor": "#60a5fa"
    }
  ],
  "sequence": [
    {
      "kind": "path",
      "lineId": "line-muc35kd5-rumyy5"
    },
    {
      "kind": "path",
      "lineId": "line-mudjpnj1-3r7li0"
    },
    {
      "kind": "path",
      "lineId": "line-mudinu63-lrs4g4"
    },
    {
      "kind": "path",
      "lineId": "line-mudju8n4-3zvn84"
    },
    {
      "kind": "path",
      "lineId": "line-mudipvgx-l5d8s3"
    },
    {
      "kind": "path",
      "lineId": "line-mudiv18m-fwip5k"
    }
  ],
  "fieldPoints": [],
  "version": "1.5.0",
  "timestamp": "2026-09-23T03:42:15.752Z"
}