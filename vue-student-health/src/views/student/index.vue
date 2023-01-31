<template>
  <div class="app-container">
    <div class="filter-container">
      123
    </div>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="{row}">
          <Other v-if="row.healths.length > 0" :data="row.healths" />
          <el-empty v-else description="无数据" :image-size="50"></el-empty>
        </template>
      </el-table-column>
      <el-table-column type="index" align="center" label="序号" width="50" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="birth" label="出生日期" />
      <el-table-column prop="sex" label="性别">
        <template slot-scope="{row}">
          {{ row.sex === 1 ? '男' : '女' }}
        </template>
      </el-table-column>
      <el-table-column prop="clazzName" label="班级名称" />
      <el-table-column prop="createTime" label="创建时间" />
      <el-table-column prop="updateTime" label="更新时间" />
      <el-table-column label="操作" width="300">
        <template>
          <el-button size="small" type="primary" @click="getStudents()">修改</el-button>
          <el-button size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getStudent } from '@/api/student';
import Other from '@/components/Charts/student/Other.vue';

export default {
  name: "Student",
  data() {
    return {
      tableData: []
    };
  },
  created() {
    this.getStudents();
  },
  methods: {
    getStudents() {
      getStudent({ pageNum: 1, pageSize: 50 }).then(res => {
        this.tableData = res.data.records;
      });
    }
  },
  components: { Other }
}
</script>
<style>

</style>
