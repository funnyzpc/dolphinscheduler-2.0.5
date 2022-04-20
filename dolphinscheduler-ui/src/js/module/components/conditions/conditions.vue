
<template>
  <div class="conditions-model">
      <slot name="button-group"></slot>
        <slot name="search-group" v-if="isShow"></slot>
        <template v-if="!isShow">
            <el-input
              v-model="searchVal"
              @keyup.enter.native="_ckQuery"
              placeholder="请输入关键字"
              type="text"
              style="margin-left:16px;width: 180px"
              clearable
            >
            </el-input>
          <el-button @click="_ckQuery" icon="el-icon-search"></el-button>
        </template>

  </div>
</template>
<script>
  import _ from 'lodash'
  export default {
    name: 'conditions',
    data () {
      return {
        // search value
        searchVal: ''
      }
    },
    props: {
      operation: Array
    },
    methods: {
      /**
       * emit Query parameter
       */
      _ckQuery () {
        this.$emit('on-conditions', {
          searchVal: _.trim(this.searchVal)
        })
      }
    },
    computed: {
      // Whether the slot comes in
      isShow () {
        return this.$slots['search-group']
      }
    },
    created () {
      // Routing parameter merging
      if (!_.isEmpty(this.$route.query)) {
        this.searchVal = this.$route.query.searchVal || ''
      }
    },
    components: {}
  }
</script>
