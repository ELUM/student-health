import request from '@/utils/request'

export function getStudenthealths({ pageNum = 1, pageSize = 50 }, data) {
    return request({
        url: `/studenthealth/${pageNum}/${pageSize}`,
        method: 'post',
        data
    })
}

export function addStudentHealth(data) {
    return request({
        url: '/studenthealth',
        method: 'put',
        data
    })
}

export function editStudentHealthById(data) {
    return request({
        url: '/studenthealth',
        method: 'patch',
        data
    })
}

export function deleteStudentHealthById(id) {
    return request({
        url: `/studenthealth/${id}`,
        method: 'delete'
    })
}