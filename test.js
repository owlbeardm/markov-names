#! /usr/bin/node

const k = 0;
const n = 3;
const text =
  "##liet do sta rasti nam biez starosti hod ot hoda rasti našiej bodrosti slavtie molot i stich ziemliu molodosti#";
const uniqS = [];
[...text].sort().forEach((s) => {
  if (!uniqS.includes(s)) uniqS.push(s);
});
const textLength = text.length;
const count = { "": 112 };
const N_r = [];
const beta = {};
const alpha = {};
N_r[0] = [0];

for (let j = 1; j <= n; j++) {
  for (let i = 0; i < textLength + 1 - j; i++) {
    const temp = text.substr(i, j);
    if (!count[temp]) {
      count[temp] = 0;
    }
    count[temp]++;
  }
  N_r[j] = [];
  N_r[j] = [Math.pow(uniqS.length, j)];
}

Object.keys(count).forEach((key) => {
  if (!N_r[key.length][count[key]]) N_r[key.length][count[key]] = 0;
  N_r[key.length][count[key]]++;
  N_r[key.length][0]--;
});

const getCount = (x) => {
  if (!count[x]) return 0;
  return count[x];
};

const getNr = (len, r) => {
  const nr = N_r[len][r];
  if (!nr) return 0;
  return nr;
};

const getSNr = (len, r) => {
  return getNr(len, r);
};

const getCountGT = (x) => {
  if (!getNr(x.length, 0)) {
    return getCount(x);
  }
  return (
    ((getCount(x) + 1) * getNr(x.length, getCount(x) + 1)) /
    getNr(x.length, getCount(x))
  );
};

const getD = (x) => {
  const d = getCountGT(x) / getCount(x);
  console.log(`  getD(${x})=${d}`);
  return d;
};

const getBeta = (x) => {
  if (!beta[x])
    beta[x] =
      1 -
      uniqS
        .filter((s) => getCount(x + s) > k)
        .reduce((prev, s, index) => {
          return (getD(x + s) * getCount(x + s)) / getCount(x);
        }, 0);
  console.log(`  getBeta(${x})=${beta[x]}`);
  return beta[x];
};

const getAlpha = (x) => {
  if (!alpha[x]) {
    alpha[x] =
      getBeta(x) /
      uniqS
        .filter((s) => getCount(x + s) <= k)
        .reduce((prev, s, index) => {
          return getPbo(s, x.substring(1));
        }, 0);
  }
  console.log(`  getAlpha(${x})=${alpha[x]}`);
  return alpha[x];
};

const getPbo = (s, wrds) => {
  let pob = 0;
  //   if (!wrds) pob = getCountGT(s) / text.length;
  //   else {
  if (getCount(wrds + s) > k){
    pob = (getD(wrds + s) * getCount(wrds + s)) / getCount(wrds);
  }
  else pob = getAlpha(wrds) * getPbo(s, wrds.substring(1));
  //   }
  // console.log(`getPbo(${s}, ${wrds})=${pob}`);
  return pob;
};

let getPbosum = 0;
// let cgtsum = 0;
uniqS.forEach((s1) => {
  uniqS.forEach((s2) => {
    console.log(`getPbo(${s1}, ${s2})`,getPbo(s1, s2));
    getPbosum+=getPbo(s1, s2);
  });
});

// console.log(`  getCount(hod);`, getCount("hod"));
// console.log(`getCountGT(hod);`, getCountGT("hod"));

// cgtsum += getCountGT("?");

// console.log("csum", csum);
// console.log("cgtsum", cgtsum);

console.log("getPbosum", getPbosum);
