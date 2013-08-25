/* Copyright 1986-1994 by Kai-Fu Lee, Sanjoy Mahajan, and Joe Keane.

   This file is part of Bill and is free software.  You can
   redistribute it and/or modify it under the terms of the GNU General
   Public License as published by the Free Software Foundation; either
   version 2 of the License (see the file COPYING), or (at your
   option) any later version.
 */

int Coef9[65][10] = {
          0,       0,       0,       0,       0,       0,       0,       0,       0,       0, 
          0,       0,       0,       0,       0,       0,       0,       0,       0,       0, 
          0,       0,       0,       0,       0,       0,       0,       0,       0,       0, 
          0,       0,       0,       0,       0,       0,       0,       0,       0,       0, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1383,    -171,    1798,   -1140,    3730,   -2595,     101,     -48,  -38430,  128949, 
       1188,    -326,    1821,    -671,    4001,   -3094,      93,     -48,  -38797,   17208, 
        728,    -743,    1531,    -786,    4382,   -3389,      77,     -57,  -39061,  124925, 
        713,    -697,    1527,    -760,    4592,   -3852,      67,     -52,  -38768,   48704, 
        634,    -719,    1519,   -1061,    4379,   -4072,      72,     -46,  -37700,  -80008, 
        543,    -754,    1871,   -1210,    4114,   -3727,      89,     -55,  -36266,  -80526, 
        527,    -711,    2048,   -1223,    4206,   -3356,     100,     -71,  -35288,   64742, 
        539,    -638,    1833,   -1240,    4363,   -3585,     108,     -76,  -34312,   56321, 
        555,    -566,    1811,    -887,    4740,   -3816,     113,     -81,  -34077,   73120, 
        563,    -512,    2108,    -636,    4938,   -3982,     111,     -84,  -34478,   45485, 
        561,    -474,    2311,   -1074,    4765,   -4041,     109,     -81,  -34392,  -11137, 
        523,    -477,    2581,   -1433,    4744,   -3868,     114,     -81,  -33794,   36923, 
        491,    -476,    2600,   -1456,    4908,   -3863,     130,     -86,  -33613,   73174, 
        513,    -423,    2352,   -1551,    4737,   -4064,     150,     -97,  -33840,  -33505, 
        524,    -386,    2659,   -1561,    4644,   -3887,     158,    -110,  -33445,  -18633, 
        448,    -434,    2996,   -1790,    4545,   -3741,     174,    -113,  -32172,  -18919, 
        401,    -447,    2984,   -2162,    4375,   -3712,     190,    -128,  -30912,  -30876, 
        446,    -366,    2794,   -2540,    4165,   -3762,     199,    -139,  -29824,  -70876, 
        524,    -259,    2554,   -2582,    4244,   -3735,     206,    -144,  -29386,  -37396, 
        504,    -270,    2664,   -2351,    4240,   -3787,     203,    -154,  -29399,  -54646, 
        450,    -315,    3320,   -2344,    4350,   -3669,     187,    -146,  -29088,  -13542, 
        454,    -298,    4128,   -2554,    4610,   -3579,     179,    -122,  -28413,   31528, 
        470,    -269,    4291,   -3009,    4771,   -3694,     182,    -115,  -27580,   43514, 
        475,    -250,    4265,   -3485,    4592,   -3851,     194,    -123,  -26357,  -24934, 
        511,    -206,    4603,   -3518,    4389,   -3636,     232,    -139,  -25097,  -56407, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
        567,    -151,    3294,   -4534,    4857,   -2925,     263,    -165,  -24970,  332490, 
};
