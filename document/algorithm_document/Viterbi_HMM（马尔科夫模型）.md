#马尔科夫模型（Markov Model）
统计模型，广泛应用在语音识别，词性自动标注，音字转换，概率文法等自然语言处理等应用领域

马尔科夫过程：又称马尔科夫链，指时间和状态都是离散的马尔科夫过程（后者的状态只由前者决定的过程）

##语音识别
假设s1,s2,s3为人为的声音信号，计算机接受到的信号为o1,o2,o3，要根据o1,o2,o3这组信号推测出发送的句子s1,s2,s3。在所有最有可能的句子中找出最有可能性的一个

用数学语言来描述，在已知o1,o2,o3的情况下，求使得条件概率P（s1,s2,s3|o1,o2,o3）达到最大值的那个句子

上面的条件概率不易得出，利用贝叶斯公式并且省掉常数项，可以将上述公式等价变换成P（o1,o2,o3|s1,s2,s3）P(s1,s2,s3)

P(s1,s2,s3)表示s1,s2,s3本身为合理句子的概率，P（o1,o2,o3|s1,s2,s3）表示s1,s2,s3被读成o1,o2,o3的概率

###隐含马尔科夫（HMM）

在得到上述概率后，做出两个假设简化问题：

1.s1,s2,s3是一个马尔科夫链，即si的状态只与si-1有关

2.接受信号oi只与发送信号si有关

满足这两个假设的模称为隐含马尔科夫模型，因为s1,s2,s3是无法直接观测到的，所以称为隐含，s1,s2,s3为隐含马尔科夫链，可以根据Viterbi算法，得到概率P（o1,o2,o3|s1,s2,s3）=P(o1|s1)P(o2|s2)P(o3|s3)

##Viterbi算法
Viterbi算法算法可以用于解决隐含马尔科夫问题，也可以解决其他问题，同样隐含马尔科夫可以用其他方法（穷举）解决，两者不等价

Viterbi算法的核心思想为通过可观察状态的概率序列判断隐含状态的概率序列， 由动态规划的方法来寻找出现概率最大的隐藏状态序列

根据 Viterbi 理论，后一天的状态会依赖前一天的状态和当前的可观察的状态。

###举例：
>假设老王的身体情况只有两种情况，“健康”或“发烧”，当医生询问老王的感觉来判断他是否生病时，老王只能表达“正常”，“头晕”或“冷”，第一天老王告诉医生“正常”，第二天“冷”，第三天“头晕”，**医生如何根据描述来判断老王这三天的身体情况？**

>已知老王的身体状态转换概率:

>P(健康-健康)=0.7，P（健康-发烧）=0.3，P（发烧-健康）=0.4，P(发烧-发烧)=0.6

>已知在两种条件下，老王的感觉的分布概率：

>P(正常|健康)=0.5，P（冷|健康）=0.4，P(头晕|健康)=0.1，P（正常|发烧）=0.1，P(冷|发烧)=0.3，P（头晕|发烧）=0.6

>已知老王每天身体状态的概率分布：

>P(健康)=0.6，P(发烧)=0.4

###过程：
1.第一天，在老王感觉正常时，身体状态概率分布：

P(今天健康)=P（健康）P(正常|健康)=0.6 X 0.5=0.3

P(今天发烧)=P（发烧）P(正常|发烧)=0.4 X 0.1 = 0.04

>第一天老王健康的概率明显高于发烧，所以判断老王第一天健康

2.第二天，在老王感觉冷的情况下，身体状态概率分布

P（今天发烧|前一天发烧）=P（前一天发烧）P(发烧-发烧)P(冷|发烧)=0.04 X 0.6 X 0.3 = 0.0072

P（今天健康|前一天发烧) =P(前一天发烧)P(发烧-健康)P(冷|健康)=0.04 X 0.4 X 0.4 = 0.0064

**P（今天发烧|前一天健康）=P（前一天健康）P(健康-发烧)P(冷|发烧)=0.3 X 0.3 X 0.3 = 0.027**

**P（今天健康|前一天健康）=P(前一天健康) P（健康-健康）P（冷|健康）=0.3 X 0.7 X 0.4 = 0.084**

>第二天老王健康的概率明显较高，判断老王第二天健康

3.第三天，在老王感觉头晕的情况下，身体状态概率分布

P（今天发烧|前一天发烧）=P（前一天发烧）P(发烧-发烧)P(头晕|发烧)=0.027 X 0.6 X 0.6 = 0.00972

P（今天健康|前一天发烧) =P(前一天发烧)P(发烧-健康)P(头晕|健康)=0.027 X 0.4 X 0.1 = 0.00108

**P（今天发烧|前一天健康）=P（前一天健康）P(健康-发烧)P(头晕|发烧)=0.084 X 0.3 X 0.6 = 0.01512**

**P（今天健康|前一天健康）=P(前一天健康) P（健康-健康）P（头晕|健康）=0.084 X 0.7 X 0.1 = 0.00588**

>第三天老王的身体状态为发烧

**判断三天老王的身体状态为：健康，健康，发烧**

##Viterbi算法实现

已知三个硬币A,B,C,每个硬币有正反两面，三个硬币放入盒子中，随机取出一个硬币抛下，进行三次，得到正反情况为“正，反，正”，求三个硬币最有可能的取出顺序，已知初始概率分布（每次取出硬币的概率分布），硬币之间的状态转移概率（例如本次取出1号，下次取出2号的概率），硬币内部正反面的概率分布（一个硬币内，这次抛出了正面，下次抛出反面的概率）

测试类

	public class MarkovModel {

		public static void main(String[] args) {
			/*
			 * 隐含层状态系列是1，2，3 O＝(正，反，正)，现在求隐含层的转移情况其中正记作0，反记为1 则观察变为0，1，0
			 */
			double[] pi = { 0.2, 0.4, 0.4 }; // 初始矩阵 1,2,3的初始概率
	
			// 1 2 3
			double[][] A = { { 0.5, 0.2, 0.3 }, // 1
					{ 0.3, 0.5, 0.2 }, // 2
					{ 0.2, 0.3, 0.5 } };// 3 隐含层内部转移矩阵
	
			// 红 白
			double[][] B = { { 0.5, 0.5 }, // 1
					{ 0.4, 0.6 }, // 2
					{ 0.7, 0.3 } };// 3 发射矩阵
	
			int[] O = { 0, 1, 0 };
	
			// weight横表示隐含成状态，竖方向表示观察状态值得状态
	
			double[][] weight = new double[A.length][O.length];
			// 当t=0的时候开始计算，初始化
	
			for (double p : pi) {
				for (int i = 0; i < weight.length; i++) {
					weight[i][O[0]] = pi[i] * B[i][O[0]];
				}
	
			}
	
			for (int t = 1; t < O.length; t++) { // t=0状态由初始化，故又t=1开始
				for (int j = 0; j < A.length; j++) {// 这里A始终是一个方阵
					weight[j][t] = Double.MIN_VALUE;
					for (int k = 0; k < A.length; k++) {
						// 这里一定要清楚 hmm的前一个状态*由k转移到j状态*发射概率
						// ，比如说j=1的时候，表示由1转移到1，和又2转移到1的状态
						double temp = weight[k][t - 1] * A[k][j] * B[j][O[t]];
						if (temp > weight[j][t]) {
							weight[j][t] = temp;
						}
					}
	
				}
			}
	
			// 打印weight矩阵
			for (int i = 0; i < weight.length; i++) {
				for (int j = 0; j < weight[0].length; j++) {
					System.out.print(weight[i][j] + " ");
				}
				System.out.println();
			}
	
		}
	}

执行结果：
	
	0.1 0.027999999999999997 0.007559999999999999 
	0.16000000000000003 0.05039999999999999 0.010079999999999999 
	0.27999999999999997 0.041999999999999996 0.014699999999999998 

得出结论，第一次抛出1号，第二次抛出1号，第三次抛出1号的概率最大，最有可能的抛出顺序：1,1,1

##古德-图灵估计（Good-Turing Estimate）
对于没有看见的事件，不能认为它发生的概率就是零，因此从概率的总量中，分配一个很小的比例给予这些没有看见的事件，这样一来，看见的那些事件的概率总和就要小于1了，因此需要将所有看见的事件概率调小一些。至于小多少，要根据“越是不可信的统计折扣越多”的方式进行

实际使用中，一般对出现次数超过某个阈值的词，频率不下调，只对出现次数低于这个阈值的词，词频才下调，下调得到的频率总和给未出现的词
	