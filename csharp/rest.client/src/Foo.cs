using System;

namespace rest.client
{
    public class Foo
    {
       public string MethodNotTested(string s) {
            string result = "not covered";
            if (s == "5150") {
                result = "fake work";
            }
            return result;
       }

       public string CanaryRepeat(string s, int n) 
        {
            string result = "";
            for (int i = 1; i <= n; i++) {
                result += s;
            }
            return result;
       }
    }
}
