using System;
using System.IO;
using System.Threading.Tasks;
using System.Threading;

namespace async.single
{
    public class Sleeper
    {
        private int id;
        private int delayInMillis;

        public Sleeper(int id, int delayInMillis)
        {
            this.id = id;
            this.delayInMillis = delayInMillis;
        }

        public void Go()
        {
            Sleep(delayInMillis);
        }

        void Sleep(int delayInMillis)
        {
            var isEnabled = true;
            if (isEnabled)
            {
                Logger.Log($"id: {id} sleeper start");
                Thread.Sleep(delayInMillis);
                Logger.Log($"id: {id} sleeper done");
            }
        }
    }
}
