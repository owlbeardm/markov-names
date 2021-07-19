# markov-names


## Katz's back-off model

The equation for Katz's back-off model is:

$
P_{bo}(w_i \mid w_{i-n+1} \cdots w_{i-1})= 
\begin{cases}
  d_{w_{i-n+1} \cdots w_i} \cfrac{C(w_{i-n+1} \cdots w_{i-1}w_i)}{C(w_{i-n+1} \cdots w_{i-1})}, \text{ if } C(w_{i-n+1} \cdots w_i)>k \\
  \alpha_{w_{i-n+1} \cdots w_{i-1}}P_{bo}(w_i \mid w_{i-n+2} \cdots w_{i-1}), \text{ otherwise}
\end{cases}
$

where:

$C(x)=count(x) \\
w_j=j\text{-th word}$

parametrs:

- <img src="https://latex.codecogs.com/gif.latex?s=\text { $k=0$ }  " />

- $k=0$

- $\alpha_{w_{i-n+1} \cdots w_{i-1}}=\cfrac{\beta_{w_{i-n+1} \cdots w_{i-1}}}{\displaystyle\sum_{\{w_i:C(w_{i-n+1} \cdots w_i)\le k \}} P_{bo}(w_i \mid w_{i-n+2} \cdots w_{i-1})}$  
  where $\beta_{w_{i-n+1} \cdots w_{i-1}} = 1 - \displaystyle\sum_{\{w_i:C(w_{i-n+1} \cdots w_i) > k \}}d_{w_{i-n+1} \cdots w_i} \cfrac{C(w_{i-n+1} \cdots w_{i-1}w_i)}{C(w_{i-n+1} \cdots w_{i-1})}$

- $d_{w_{i-n+1} \cdots w_i}=\cfrac{C^*(w_{i-n+1} \cdots w_i)}{C(w_{i-n+1} \cdots w_i)}$  
  $C^*(x)=(C(x)+1) \cfrac{N_{C(x)+1}}{N_{C(x)}}$  
  $N_{C(x)}=|\{ y \mid C(y)=C(x) \}|$  
  $N_{0}=X-\displaystyle\sum_{C=1}^\infin N_C$
