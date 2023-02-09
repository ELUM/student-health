import request from '@/utils/request'

export function getStudent({ pageNum = 1, pageSize = 50 }, data) {
  return request({
    url: `/student/${pageNum}/${pageSize}`,
    method: 'post',
    data
  })
}

export function addStudent(data) {
  return request({
    url: '/student',
    method: 'put',
    data
  })
}

export function deleteStudentById(id) {
  return request({
    url: `/student/${id}`,
    method: 'delete'
  })
}

export function editStudent(data) {
  return request({
    url: '/student',
    method: 'patch',
    data
  })
}
