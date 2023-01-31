import request from '@/utils/request'

export function getStudent({pageNum = 1, pageSize = 50}, data) {
  return request({
    url: `/student/${pageNum}/${pageSize}`,
    method: 'post',
    data
  })
}
