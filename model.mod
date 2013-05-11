param cc;
param pp;
param rr;
param mm;
param prc;

param ND{r in 1..rr, c in 1..cc};
param SP{ r in 1..rr, m in 1..mm};
param CS{c in 1..cc,m in 1..mm};

param CN{c in 1..cc};

var X{c in 1..cc, m in 1..mm} >=0 integer; 
var COST;
var XS_FREE{c in 1..cc, m in 1..mm} >=0 integer;
var XS_COST{c in 1..cc, m in 1..mm} >=0 integer;

subject to X_STORED_FREE_OF_CHARGE_1{mn in 1..2}: sum{c in 1..cc, m in 1..mn} XS_FREE[c,m] <=150; 
subject to XX{m in 1..mm, c in 1..cc}: X[c,m] = XS_FREE[c,m]+ XS_COST[c,m]; 
subject to CONTRACT{c in 1..cc}: sum{m in 1..mm} X[c,m] >= CN[c];
subject to BOUNDS{r in 1..rr, m in 1..mm}: sum{c in 1..cc} X[c,m]*ND[r,c] <= SP[r,m];
subject to COST_BOUND: COST = sum{c in 1..cc, m in 1..mm} (X[c,m]*CS[c,m]) + prc*(sum{c in 1..cc}(XS_COST[c,1]*CS[c,1] + (XS_COST[c,1]+XS_COST[c,2])*CS[c,2]));

minimize cel: COST;