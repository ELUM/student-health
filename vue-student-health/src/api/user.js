import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/vue-element-admin/user/logout',
    method: 'post'
  })
}

export function getUser({ pageNum = 1, pageSize = 50 }, data) {
  return request({
    url: `/users/${pageNum}/${pageSize}`,
    method: 'post',
    data
  })
}

export function editUser(data) {
  return request({
    url: '/user',
    method: 'patch',
    data
  })
}

export function addUser(data) {
  return request({
    url: '/user',
    method: 'put',
    data
  })
}

export function deleteUserById(id) {
  return request({
    url: `/user/${id}`,
    method: 'delete',
  })
}
