
<template>
  <m-conditions>
    <template slot="search-group">
        <el-date-picker
          style="width: 310px"
          v-model="dataTime"
          @change="_onChangeStartStop"
          type="datetimerange"
          range-separator="-"
          :start-placeholder="$t('startDate')"
          :end-placeholder="$t('endDate')"
          value-format="yyyy-MM-dd HH:mm:ss">
        </el-date-picker>
        <el-select style="width: 140px;" @change="_onChangeState" :value="searchParams.stateType" :placeholder="$t('State')" >
          <el-option
                  v-for="city in stateTypeList"
                  :key="city.label"
                  :value="city.code"
                  :label="city.label">
          </el-option>
        </el-select>
        <el-input v-model="searchParams.host" @keyup.enter.native="_ckQuery" style="width: 140px;" :placeholder="$t('host')"></el-input>
        <el-input v-model="searchParams.executorName" @keyup.enter.native="_ckQuery" style="width: 140px;" :placeholder="$t('Executor')"></el-input>
        <el-input v-model="searchParams.processInstanceName" @keyup.enter.native="_ckQuery" style="width: 160px;" placeholder="工作流实例"></el-input>
        <el-input v-model="searchParams.searchVal" @keyup.enter.native="_ckQuery" style="width: 160px;"  placeholder="名称"></el-input>
      <el-button @click="_ckQuery" icon="el-icon-search"></el-button>

    </template>
  </m-conditions>
</template>
<script>
  import _ from 'lodash'
  import { stateType } from './common'
  import mConditions from '@/module/components/conditions/conditions'
  export default {
    name: 'task-instance-conditions',
    data () {
      return {
        // state(list)
        stateTypeList: stateType,
        searchParams: {
          // state
          stateType: '',
          // start date
          startDate: '',
          // end date
          endDate: '',
          // search value
          searchVal: '',
          // host
          host: '',
          // executor name
          executorName: '',
          processInstanceName: ''
        },
        dataTime: []
      }
    },
    props: {},
    methods: {
      _ckQuery () {
        this.$emit('on-query', this.searchParams)
      },
      /**
       * change times
       */
      _onChangeStartStop (val) {
        this.searchParams.startDate = val[0]
        this.searchParams.endDate = val[1]
        this.dataTime[0] = val[0]
        this.dataTime[1] = val[1]
      },
      /**
       * change state
       */
      _onChangeState (val) {
        this.searchParams.stateType = val
      }
    },
    watch: {
    },
    created () {
      // Routing parameter merging
      if (!_.isEmpty(this.$route.query)) {
        this.searchParams = _.assign(this.searchParams, this.$route.query)
        if (this.searchParams.endDate && this.searchParams.startDate) {
          this.dataTime[0] = this.searchParams.startDate
          this.dataTime[1] = this.searchParams.endDate
        }
      }
    },
    mounted () {
    },
    computed: {
    },
    components: { mConditions }
  }
</script>
