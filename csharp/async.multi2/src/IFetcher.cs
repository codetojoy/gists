using System.Threading.Tasks;

namespace async.multi2
{
    public interface IFetcher
    {

        Task<int> Fetch();
    }
}
