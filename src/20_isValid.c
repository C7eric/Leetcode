char pairs(char a){

    if(a == '}')    return '{';
    if(a == ']')    return '[';
    if(a == ')')    return '(';
    return 0;
}

bool isValid(char* s){

    int n = strlen(s);
    if(n % 2 == 1){
        return  false;
    }
    int stk[n+1],top = 0;
    for(int i = 0;i < n;i++){
        char ch = pairs(s[i]);
        if(ch){
            if(top == 0 || stk[top - 1] != ch]){
                return false;
            }
            top--;
        }
        else {
            stk[top++] = s[i];
        }
        return top == 0;
    }

/*
20. 有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
 

示例 1：

输入：s = "()"
输出：true
示例 2：

输入：s = "()[]{}"
输出：true
示例 3：

输入：s = "(]"
输出：false
示例 4：

输入：s = "([)]"
输出：false
示例 5：

输入：s = "{[]}"
输出：true
 

提示：

1 <= s.length <= 104
s 仅由括号 '()[]{}' 组成
*/


