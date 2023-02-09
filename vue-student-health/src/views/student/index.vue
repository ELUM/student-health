<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input size="small" v-model="search.name" placeholder="查询学生姓名" style="width: 110px;margin-right: 10px;" />
      <el-autocomplete size="small" v-model="search.clazzname" @select="handleSelect"
        :fetch-suggestions="querySearchAsync" placeholder="查询班级" style="width: 100px;margin-right: 10px;" />
      <el-button type="primary" size="small" @click="searchStudent()">查询</el-button>
      <el-button type="success" size="small" @click="addVisible = true">添加学生</el-button>
      <el-button size="small" icon="el-icon-refresh" @click="getStudents()">刷新</el-button>
    </div>
    <el-table :data="tableData" ref="studentTableRef" height="750" border style="width: 100%" v-loading="tableLoading">
      <el-table-column type="expand">
        <template slot-scope="{row}">
          <el-empty v-if="!row.healths.length > 0" description="无数据" :image-size="50"></el-empty>
          <div v-else style="display:flex">
            <Other :data="row.healths" />
            <BMI :data="row.healths" />
          </div>
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
        <template slot-scope="{row}">
          <el-button size="small" type="success" @click="addHealth(row)">添加体测数据</el-button>
          <el-button size="small" type="primary" @click="editStudent(row)">修改</el-button>
          <el-button size="small" type="danger" @click="deleteStudent(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination layout="total, prev, pager, next" background :page-size="pageSize" :total="total"
      @current-change="changePage">
    </el-pagination>

    <el-dialog title="添加学生" :visible.sync="addVisible" width="400px">
      <el-form :model="addForm" label-width="80px" :rules="rules" ref="addRuleForm">
        <el-form-item label="学生姓名" required prop="name">
          <el-input v-model="addForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" required prop="sex">
          <el-switch active-text="女" inactive-text="男" :inactive-value="1" :active-value="0"
            v-model="addForm.sex"></el-switch>
        </el-form-item>
        <el-form-item label="出生日期" required prop="birth">
          <el-date-picker v-model="addForm.birth" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"
            type="datetime" placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="班级" required prop="cid">
          <el-select v-model="addForm.cid" filterable remote reserve-keyword placeholder="请输入班级"
            :remote-method="remoteMethod" :loading="addLoading">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="addVisible = false">取 消</el-button>
        <el-button type="primary" @click="HandleAddStudent(addForm)">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改学生" :visible.sync="editVisible" width="400px">
      <el-form :model="editForm" label-width="80px" :rules="rules" ref="editRuleForm">
        <el-form-item label="学生姓名" required prop="name">
          <el-input v-model="editForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" required prop="sex">
          <el-switch active-text="女" inactive-text="男" :inactive-value="1" :active-value="0"
            v-model="editForm.sex"></el-switch>
        </el-form-item>
        <el-form-item label="出生日期" required prop="birth">
          <el-date-picker v-model="editForm.birth" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss"
            type="datetime" placeholder="选择日期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="班级" required prop="clazzName">
          <el-select v-model="editForm.clazzName" filterable remote reserve-keyword placeholder="请输入班级"
            :remote-method="remoteMethod" :loading="addLoading">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="HandleEditStudent(editForm)">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改体测数据" :visible.sync="addHealthVisible" width="400px">
      <el-form :model="addHealthForm" label-width="80px" :rules="healthRules" ref="addRuleHealthForm">
        <el-form-item label="姓名" required prop="name">
          {{ addHealthForm.name }}
        </el-form-item>
        <el-form-item label="性别" required prop="sex">
          {{ addHealthForm.sex === 1 ? '男' : '女' }}
        </el-form-item>
        <el-form-item label="肺活量" required prop="vitalCapacity">
          <el-input-number v-model="addHealthForm.vitalCapacity" :step="1" :min="0"></el-input-number>ml
        </el-form-item>
        <el-form-item label="50米" required prop="shortRun">
          <el-input-number v-model="addHealthForm.shortRun" :step="0.01" :min="0"></el-input-number>秒
        </el-form-item>
        <el-form-item label="坐位体前屈" required prop="bodyFlexion">
          <el-input-number v-model="addHealthForm.bodyFlexion" :step="0.1" :min="-10"></el-input-number>cm
        </el-form-item>
        <el-form-item label="立定跳远" required prop="standJump">
          <el-input-number v-model="addHealthForm.standJump" :step="0.1" :min="0"></el-input-number>m
        </el-form-item>
        <el-form-item label="引体向上/仰卧起坐" required prop="up">
          <el-input-number v-model="addHealthForm.up" :step="1" :min="0"></el-input-number>个
        </el-form-item>
        <el-form-item label="1000/800米" required prop="longRun">
          <el-input-number v-model="addHealthForm.longRun" :step="0.01" :min="0"></el-input-number>分
        </el-form-item>
        <el-form-item label="身高" required prop="height">
          <el-input-number v-model="addHealthForm.height" :step="1" :min="0"></el-input-number>cm
        </el-form-item>
        <el-form-item label="体重" required prop="weight">
          <el-input-number v-model="addHealthForm.weight" :step="0.01" :min="0"></el-input-number>kg
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="HandleAddHealth(addHealthForm)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { getStudent, deleteStudentById, addStudent, editStudent } from '@/api/student';
import { addStudentHealth } from '@/api/studenthealth';
import { getClazzByName } from '@/api/clazz';
import BMI from '@/components/Charts/student/BMI.vue';
import Other from '@/components/Charts/student/Other.vue';

export default {
  name: "Student",
  components: { Other, BMI },
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 50,
      tableLoading: true,
      search: {
        name: '',
        cid: null,
        clazzname: ''
      },
      clazzs: [],
      options: [],
      addLoading: false,
      addVisible: false,
      addForm: {},
      editVisible: false,
      editForm: {},
      rules: {
        name: [
          { required: true, message: '请输入学生姓名', trigger: 'blur' },
        ],
        sex: [
          { required: true, message: '请选择性别', trigger: 'blur' }
        ],
        birth: [
          { required: true, message: '请选择日期', trigger: 'blur' }
        ],
        cid: [
          { required: true, message: '请选择班级', trigger: 'blur' }
        ],
      },
      addHealthVisible: false,
      addHealthForm: {},
      healthRules: {
        vitalCapacity: [
          { required: true, message: '请输入数据', trigger: 'blur' },
        ],
        shortRun: [
          { required: true, message: '请输入数据', trigger: 'blur' },
        ],
        bodyFlexion: [
          { required: true, message: '请输入数据', trigger: 'blur' },
        ],
        standJump: [
          { required: true, message: '请输入数据', trigger: 'blur' },
        ],
        up: [
          { required: true, message: '请输入数据', trigger: 'blur' },
        ],
        longRun: [
          { required: true, message: '请输入数据', trigger: 'blur' },
        ],
        height: [
          { required: true, message: '请输入数据', trigger: 'blur' },
        ],
        weight: [
          { required: true, message: '请输入数据', trigger: 'blur' },
        ],
      }
    };
  },
  created() {
    this.getStudents();
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    getStudents() {
      let pageNum = this.pageNum
      let pageSize = this.pageSize
      this.tableLoading = true
      if (this.search.clazzname === '' && this.search.name === '') {
        getStudent({ pageNum: pageNum, pageSize: pageSize }).then(res => {
          this.tableData = res.data.records;
          this.total = res.data.total
          this.tableLoading = false
        });
      } else {
        getStudent({ pageNum: pageNum, pageSize: pageSize }, this.search).then(res => {
          this.tableData = res.data.records;
          this.total = res.data.total
          this.tableLoading = false
        });
      }
    },
    changePage(val) {
      this.pageNum = val
      this.$refs.studentTableRef.bodyWrapper.scrollTop = 0
      this.getStudents(this.search)
    },
    deleteStudent(id) {
      this.$confirm('此操作将永久删除该学生和检测数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteStudentById(id).then(res => {
          if (res.code === 200) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          } else {
            this.$message({
              type: 'warning',
              message: '删除失败!'
            });
          }
          this.getStudents()
        })
      }).catch(() => {
      });
    },
    remoteMethod(query) {
      this.addLoading = true
      getClazzByName(query).then(res => {
        this.options = []
        this.addLoading = false
        for (const item of res.data) {
          this.options.push(
            {
              label: item.name,
              value: item.id
            }
          )
        }
      })
    },
    querySearchAsync(queryString, cb) {
      this.clazzs = []
      getClazzByName(queryString).then(res => {
        for (const item of res.data) {
          this.clazzs.push(
            {
              value: item.name,
              id: item.id
            }
          )
        }
        cb(this.clazzs)
      })
    },
    handleSelect(item) {
      this.search.cid = item.id
    },
    searchStudent() {
      this.pageNum = 1

      if (this.search.clazzname === '' && this.search.name === '') {
        this.getStudents()
      } else {
        if (this.search.clazzname === '') {
          delete this.search.cid
        }

        this.getStudents()
      }
    },
    HandleAddStudent(data) {
      this.$refs['addRuleForm'].validate(valid => {
        if (valid) {
          addStudent(data).then(res => {
            if (res.code == 200) {
              this.$message({
                type: 'success',
                message: '添加成功!'
              });
            } else {
              this.$message({
                type: 'warning',
                message: '添加失败!'
              });
            }
            this.getStudents()
            this.resetForm('addRuleForm')
          })
        }
      })
    },
    editStudent(data) {
      this.editForm = { ...data }
      this.editVisible = true
    },
    HandleEditStudent(data) {
      this.$refs['editRuleForm'].validate(valid => {
        if (valid) {
          editStudent(data).then(res => {
            if (res.code == 200) {
              this.$message({
                type: 'success',
                message: '修改成功!'
              });
            } else {
              this.$message({
                type: 'warning',
                message: '修改失败!'
              });
            }
            this.getStudents()
            this.editVisible = false
            this.resetForm('editRuleForm')
          })
        }
      })
    },
    addHealth(data) {
      this.addHealthForm = { ...data }
      delete this.addHealthForm.healths
      delete this.addHealthForm.cid
      delete this.addHealthForm.clazzName
      delete this.addHealthForm.birth
      delete this.addHealthForm.createTime
      delete this.addHealthForm.updateTime
      this.addHealthForm.sid = this.addHealthForm.id
      delete this.addHealthForm.id
      this.addHealthVisible = true
    },
    HandleAddHealth(data) {
      this.$refs['addRuleHealthForm'].validate(valid => {
        if (valid) {
          let bmi = Number.parseFloat(data.weight / ((data.height / 100) * (data.height / 100)))
          data.bmi = Math.floor(bmi * 100) / 100
          addStudentHealth(data).then(res => {
            if (res.code == 200) {
              this.$message({
                type: 'success',
                message: '添加成功!'
              });
            } else {
              this.$message({
                type: 'warning',
                message: '添加失败!'
              });
            }
            this.getStudents()
            this.addHealthVisible = false
            this.resetForm('addRuleHealthForm')
          })
        }
      })
    }
  }
}
</script>
<style>

</style>
