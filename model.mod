param cc; 	##ilość komponentów 
param rr; 	##ilość zasobów
param mm; 	##ilość miesięcy
param kk;  	## ilość kryteriów
param prc; 	##ułamek kosztów magazynowania względem wytwarzania

param ND{r in 1..rr, c in 1..cc};					## zapotrzebowanie komponentu na zasób
param SP{ r in 1..rr, m in 1..mm};					## możliwe dostawy zasobu w miesiący
param CS{c in 1..cc,m in 1..mm};					## średni koszt zasobu w miesiącu
param CN{c in 1..cc};								## zakontraktowana produkcja komponentu

param W{k in 1..kk};								## waga dla kryterium
param AS{c in 1..kk};								## poziom aspiracji / cel dla kryterium

var X{c in 1..cc, m in 1..mm} >=0 integer;			## produkcja
var XS_FREE{c in 1..cc, m in 1..mm} >=0 integer;	## produkcja wolna od opłaty za magazynowanie
var XS_COST{c in 1..cc, m in 1..mm} >=0 integer;	## produkcja z płatnym magazynowaniem
var R1{c in 1..cc}>=0 integer;						## zmienne pomocnicze by uzyskać wartość bezwzględną
var R2{c in 1..cc}>=0 integer;						## R1 + R2 = |CS - X|

var Dp{k in 1..kk} >=0 integer; 		## Dp+Dm - wartość bezwzględna F - AS
var Dm{k in 1..kk} >=0 integer;			## 
var F{k in 1..kk};						## Kryteria

subject to X_STORED_FREE_OF_CHARGE_1{mn in 1..mm}: sum{c in 1..cc, m in 1..mn} XS_FREE[c,m] <=150; 
subject to XX{m in 1..mm, c in 1..cc}: X[c,m] = XS_FREE[c,m]+ XS_COST[c,m]; 
subject to RISK_1{c in 1..cc}: R1[c]*R2[c]>=0;
subject to RISK_2{c in 1..cc}: R1[c] - R2[c] = CN[c]-sum{m in 1..mm}(X[c,m]);
subject to BOUNDS{r in 1..rr, m in 1..mm}: sum{c in 1..cc} X[c,m]*ND[r,c] <= SP[r,m];
subject to COST_BOUND: F[1] = sum{c in 1..cc, m in 1..mm} (X[c,m]*CS[c,m]) + prc*(sum{c in 1..cc}(XS_COST[c,1]*CS[c,1] + (XS_COST[c,1]+XS_COST[c,2])*CS[c,2] + ((XS_COST[c,1]+XS_COST[c,2]+XS_COST[c,2])*CS[c,3])));
subject to RISK_BOUND: F[2] = sum{c in 1..cc} (R1[c]+R2[c]);

subject to D1{k in 1..kk}: Dp[k] - Dm[k] = F[k] - AS[k];
subject to D2{k in 1..kk}: Dp[k] * Dm[k] >= 0;

minimize cel: sum {k in 1..kk} W[k]*(Dp[k]+Dm[k]);