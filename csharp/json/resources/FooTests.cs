using NUnit.Framework;

using json;

namespace json
{
    public class FooTests
    {
        [Test]
        public void Test_CanaryRepeat()
        {
            var foo = new Foo();

            // test
            string result = foo.CanaryRepeat("abc", 3);

            Assert.AreEqual("abcabcabc", result);
        }
    }
}
