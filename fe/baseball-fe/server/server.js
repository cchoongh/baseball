const path = require('path');
const express = require('express');
const app = express();
const cors = require('cors');
const morgan = require('morgan');

app.use(cors());
app.use(morgan('combined'));
app.use(express.urlencoded({ extended: false }));
app.use(express.json());

app.get('/matches', (req, res) => {
  const json = {   
    matches : [
        {
          id : 1,
          home :  {
              id: 1,
              name : "hanwha",
              selected : false
            },
          away : {
            id: 2,
            name : "doosan",
            selected : true
          }
        },
        {
            id : 2,
            home :  {
                id : 3,
                name : "kia",
                selected : true
            },
            away : {
              id: 4,
              name : "samsung",
              selected : true
            }
        },
        {
            id : 3,
            home : {
              id : 5,
              name : "lotte",
              selected : true
            },
            away : {
              id : 6,
              name : "lg",
              selected : true           
            }
        }
    ]
}   
  res.json(json);
});

app.get('/games/:id/start', (req, res) => {
  const json = {   
    "game_info" : {
        "id" : 1,
        "current_inning": 1,
        "frame" : "top"
    },
    "home" : {
      "name" : "hanwha",
      "mode" : "fielding",
      "score" : 0,
      "pitcher" : {
        "id" : 1, 
        "name" : "김광진" 
      },
      "batters" : [
        {
          "id" : 1,
          "name" : "김광진"
        },
        {
          "id" : 2,
          "name" : "크롱롱"
        },
        {
          "id" : 3,
          "name" : "제이세이"
        },
        {
          "id" : 4,
          "name" : "나이스"
        },
        {
          "id" : 5,
          "name" : "디코파기"
        },
        {
          "id" : 6,
          "name" : "봉프라이팬"
        },
        {
          "id" : 7,
          "name" : "햄버거지"
        },
        {
          "id" : 8,
          "name" : "치킨더가든"
        },
        {
          "id" : 9,
          "name" : "피자콜라면"
        }
      ]       
    },
    "away" : {
        "name" : "doosan",
        "mode" : "batting",
        "score" : 0,
        "pitcher" : {
          "id" : 1, 
          "name" : "김광진" 
        },
        "batters" : [
          {
            "id" : 10,
            "name" : "호눅스"
          },
          {
            "id" : 11,
            "name" : "크롱"
          },
          {
            "id" : 12,
            "name" : "제이케이"
          },
          {
            "id" : 13,
            "name" : "네이스"
          },
          {
            "id" : 14,
            "name" : "디코"
          },
          {
            "id" : 15,
            "name" : "봉프"
          },
          {
            "id" : 16,
            "name" : "햄버거"
          },
          {
            "id" : 17,
            "name" : "치킨"
          },
          {
            "id" : 18,
            "name" : "피자콜라"
          }
        ]       
    }
  };
  res.json(json);
});

app.listen(3030, () => { console.log('express server is running.'); });