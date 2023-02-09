import defaultSettings from '@/settings'

const title = defaultSettings.title || '学生体测管理平台'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}
