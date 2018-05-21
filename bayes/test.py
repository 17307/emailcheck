#-*- coding: utf-8 -*-
from sklearn.externals import joblib

ci = joblib.load(r'ci.model')
idf = joblib.load(r'idf.model')
model = joblib.load(r'myemail2.model')

fenci_subject = open(r"F:\Users\lunwen\mailout\bayes.mail", "r", encoding="utf8")
result = []
for i in fenci_subject.readlines():
    # 去除[\n]
    sub_i = i[1:-2]
    # 把,改为" "
    subj = sub_i.replace(",", "")
    x = ci.transform([subj])
    i = idf.transform(x)

    #print(subj)
    #print(model.predict(i))
    result.append(model.predict(i)[0])
print(result)