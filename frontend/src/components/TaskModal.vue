<template>
  <div
    v-if="isOpen"
    class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50"
  >
    <div class="bg-white rounded-lg shadow-lg w-11/12 md:w-1/2 lg:w-1/3">
      <div class="flex justify-between items-center p-4 border-b">
        <h3 class="text-lg font-medium">{{ title }}</h3>
        <button @click="closeModal" class="text-black">&times;</button>
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
