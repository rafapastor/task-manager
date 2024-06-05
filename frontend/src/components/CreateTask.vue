<template>
  <div class="form-container">
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="title">Title:</label>
        <input type="text" v-model="taskData.task.title" class="form-control" />
        <span v-if="taskData.errors.title" class="error-message">{{
          taskData.errors.title
        }}</span>
      </div>
      <div class="form-group">
        <label for="description">Description:</label>
        <input
          type="text"
          v-model="taskData.task.description"
          class="form-control"
        />
      </div>
      <div class="form-group">
        <label for="dueDate">Due Date:</label>
        <input
          type="date"
          v-model="taskData.task.dueDate"
          class="form-control"
        />
        <span v-if="taskData.errors.dueDate" class="error-message">{{
          taskData.errors.dueDate
        }}</span>
      </div>
      <button type="submit" class="btn btn-primary">Add Task</button>
    </form>
    <div v-if="taskData.serverError" class="server-error">
      {{ taskData.serverError }}
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useTaskForm } from "@/composables/useTaskForm";

export default defineComponent({
  setup(_, { emit }) {
    const { taskData, submitTaskForm } = useTaskForm();

    const submitForm = async () => {
      await submitTaskForm();
      emit("task-created");
    };

    return {
      taskData,
      submitForm,
    };
  },
});
</script>
