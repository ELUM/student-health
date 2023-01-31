import request from '@/utils/request'

export function getStudenthealthById({ pageNum = 1, pageSize = 10 }, sid) {
    return request({
        url: `/studenthealth/${pageNum}/${pageSize}`,
        method: 'post',
        data: {
            sid
        }
    })
}