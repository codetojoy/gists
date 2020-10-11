import Layout from '../../components/layout'
import { getAllPostIds, getPostData } from '../../lib/posts'

// this displays a post
export default function Post({ postData }) {
    return (
      <Layout>
          <article>
                <div dangerouslySetInnerHTML={{ __html: postData.contentHtml }} />
          </article>
      </Layout>
    )
}

// can't delete this or else there is a problem with 'fs' module
export async function getStaticPaths() {
  const paths = getAllPostIds()
  return {
    paths,
    fallback: false
  }
}

export async function getStaticProps({ params }) {
  // this finds all of the Markdown files:
  const postData = await getPostData(params.id)
  return {
    props: {
      postData
    }
  }
}
