<template>
    <div id="BMI" :style="{ height: '250px', width: '500px' }"></div>
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
            let bmi = []
            let height = []
            let weight = []
            let times = []

            for (const iterator of this.data) {
                height.push(iterator.height)
                weight.push(iterator.weight)
                bmi.push(iterator.bmi)
                times.push(String(iterator.createTime).slice(0, 10))
            }

            this.chart = echarts.init(document.getElementById('BMI'))
            let option = {
                title: {
                    text: 'BMI'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    // data: ['肺活量', '50米', '坐位体前屈', '立定跳远', '引体向上/仰卧起坐', '1000米/800米']
                    data: ['BMI', '身高', '体重(千克)']
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
                        name: 'BMI',
                        type: 'line',
                        stack: 'Total',
                        data: bmi,
                        smooth: true
                    },
                    {
                        name: '身高',
                        type: 'line',
                        stack: 'Total',
                        data: height,
                        smooth: true
                    },
                    {
                        name: '体重(千克)',
                        type: 'line',
                        stack: 'Total',
                        data: weight,
                        smooth: true
                    },
                ]
            };

            this.chart.setOption(option)
        }
    },
}
</script>
<style>

</style>