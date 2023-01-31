<template>
    <div id="other" :style="{ height: '500px', width: '800px' }"></div>
</template>
<script>
import echarts from 'echarts'

export default {
    data() {
        return {
            chart: null
        }
    },
    props: ['data'],
    mounted() {
        this.initCharts()
    },
    methods: {
        initCharts() {
            // 肺活量
            let vitalCapacity = []
            // 短跑
            let shortRun = []
            // 坐位体前屈
            let bodyFlexion = []
            // 立定跳远
            let standJump = []
            // 引体向上/仰卧起坐
            let up = []
            // 长跑
            let longRun = []
            // 次数
            let times = []

            for (const iterator of this.data) {
                vitalCapacity.push(Number.parseInt(iterator.vitalCapacity))
                shortRun.push(Number.parseFloat(iterator.shortRun))
                bodyFlexion.push(Number.parseFloat(iterator.bodyFlexion))
                standJump.push(Number.parseFloat(iterator.standJump))
                up.push(Number.parseInt(iterator.up))
                longRun.push(Number.parseFloat(iterator.longRun))
                times.push(String(iterator.createTime).slice(0, 10))
            }

            this.chart = echarts.init(document.getElementById('other'))
            let option = {
                title: {
                    text: '成绩'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    // data: ['肺活量', '50米', '坐位体前屈', '立定跳远', '引体向上/仰卧起坐', '1000米/800米']
                    data: ['肺活量(千)', '50米(秒)', '坐位体前屈(厘米)', '立定跳远(米)', '引体向上/仰卧起坐(个)', '1000米/800米(分)']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: times
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '肺活量(千)',
                        type: 'line',
                        stack: 'Total',
                        data: vitalCapacity,
                        smooth: true
                    },
                    {
                        name: '50米(秒)',
                        type: 'line',
                        stack: 'Total',
                        data: shortRun,
                        smooth: true
                    },
                    {
                        name: '坐位体前屈(厘米)',
                        type: 'line',
                        stack: 'Total',
                        data: bodyFlexion,
                        smooth: true
                    },
                    {
                        name: '立定跳远(米)',
                        type: 'line',
                        stack: 'Total',
                        data: standJump,
                        smooth: true
                    },
                    {
                        name: '引体向上/仰卧起坐(个)',
                        type: 'line',
                        stack: 'Total',
                        data: up,
                        smooth: true
                    },
                    {
                        name: '1000米/800米(分)',
                        type: 'line',
                        stack: 'Total',
                        data: longRun,
                        smooth: true
                    }
                ]
            };

            this.chart.setOption(option)
        }
    },
}
</script>
<style>

</style>