import { parseISO, format } from 'date-fns'

export default function Date({ dateString }) {
  let result = <time></time>
  if (dateString) {
      const date = parseISO(dateString)
      result = <time dateTime={dateString}>{format(date, 'LLLL d, yyyy')}</time>
  }
  return result
}
