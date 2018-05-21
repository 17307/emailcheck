from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.metrics import matthews_corrcoef, classification_report
from sklearn.naive_bayes import MultinomialNB
from sklearn.externals import joblib
import os


class EmailTrain():
    def __init__(self):
        # 创建多项式贝叶斯模型
        self.model = MultinomialNB()
        # 读取邮件内容 返还列表
        input_list, class_list = self.chinese_data()
        # 构造词频统计相关类
        self.vectorizer = CountVectorizer(token_pattern=r"(?u)\b\w+\b")
        # print(len(input_list))  #  61886

        # 计算个词语出现的次数
        self.X = self.vectorizer.fit_transform(input_list)
        # 获取词袋中所有文本关键词
        # self.X_word = self.vectorizer.get_feature_names()

        # 构造 TF-IDF相关类
        self.transformer = TfidfTransformer()
        # 将词频矩阵X统计成TF-IDF值
        tfidf = self.transformer.fit_transform(self.X)
        # 将内容分批进行训练 否则电脑死机…………………………………………………………………………
        for i in range(0, 50001, 100):
            temp = tfidf[i:i + 100].toarray()
            self.build_model(temp, class_list[i:i + 100])
            print("----------------")
        joblib.dump(self.vectorizer, "evaluate/ci2.model")  # 分词模型
        joblib.dump(self.transformer, "evaluate/idf2.model")  # tf-idf 模型
        joblib.dump(self.model, "evaluate/myemail22.model")  # 邮件模型

        self.model_evaluate(tfidf[50001:61886],class_list[50001:61886])

    def model_evaluate(self, x_list, y_list):
        """
        用于评估模型
        len = 61886 - 51001
        :param x_list: 用于测试的 tfide
        :param y_list: 用于测试的 class_list
        :return: 
        """
        model = joblib.load('evaluate/myemail22.model')
        len = 61886 - 51001
        for i in range(0, len, 100):
            p = model.predict(x_list[i:i + 100].toarray())
            s = matthews_corrcoef(p, y_list[i:i + 100])  # 准确率
            report = classification_report(y_list[i:i+100],p)
            print(s)
            print(report)


    def build_model(self, tfidf, class_list):
        """
        用于训练模型
        :param tfidf: 
        :param class_list: tfidf对应的邮件类别
        :return: 
        """
        self.model.partial_fit(tfidf, class_list, classes=[1, 0])

    def chinese_data(self):
        """
        获取邮件内容
        :return: input_list ==> 邮件的列表
                 class_list ==> 邮件对应类别
        """

        input_list = []  # 邮件list
        class_list = []  # 类别list
        # 邮件类别的路径
        class_file_path = r"F:\Users\index"
        # 邮件内容路径
        path = r"F:\Users\email"
        # len = 20 正常  21 垃圾
        # spam==>0(垃圾) ham==>1
        for line in open(class_file_path, encoding='utf8'):
            line_list = line.split("/")
            # ['spam ..', 'data', '215', '097\n']
            path_temp = path + "\\" + line_list[2] + "\\" + line_list[3][0:3]
            if (os.path.isfile(path_temp)):
                f = open(path_temp, encoding='utf8', errors='ignore')
                content = f.read()
                if content != "":
                    input_list.append(content)
                    class_list.append(0 if line_list[0] == 'spam ..' else 1)
        return input_list, class_list


if __name__ == "__main__":
    EmailTrain()
    """
    1------zhengchang
    0------laji
    
    ci = joblib.load('ci.model')
    idf = joblib.load('idf.model')
    model = joblib.load('myemail2.model')
    s = "暖冬 福利 待 查收 网易 自营，MUJI 等 制造商 直供 10余款 保暖 用品 免费 领 马上 领取"
    s2 = "今天 是 个 好 日子"
    s3 = "你 新年 礼物 到 元 免费 带 回 家"
    s4 = "师德 师风 研讨"
    x = ci.transform([s4])
    i = idf.transform(x)
    print(model.predict(i))
    """