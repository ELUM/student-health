<template>
  <div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column type="index" align="center" label="序号" width="50" />
      <el-table-column v-for="item in structure" :prop="item.prop" :label="item.label" :width="item.width" />
      <el-table-column label="操作" width="300">
        <template>
          <el-button>123</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>

export default {
  name: 'DemoTable',
  props: {

  },
  data() {
    return {
      structure: [
        {
          prop: 'date',
          label: '日期'
        },
        {
          prop: 'name',
          label: '姓名'
        },
        {
          prop: 'address',
          label: '地址'
        }
      ],
      tableData: [
        {
          date: '123',
          name: '123',
          address: '123'
        }
      ]
    }
  },
  methods: {

  }
}
</script>
<style lang="">

</style>
