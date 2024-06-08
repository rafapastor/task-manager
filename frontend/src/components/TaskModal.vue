<template>
  <div v-if="isOpen" class="modal">
    <div class="modal-wrapper">
      <div class="modal-header">
        <h3 class="modal-title">{{ title }}</h3>
        <button @click="closeModal" class="modal-close">&times;</button>
      </div>
      <div class="p-4">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "TaskModal",
  props: {
    isOpen: {
      type: Boolean,
      required: true,
    },
    title: {
      type: String,
      default: "Modal Title",
    },
  },
  methods: {
    closeModal() {
      this.$emit("close");
    },
  },
});
</script>
