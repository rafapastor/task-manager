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
        <textarea
          rows="4"
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
      <div v-if="taskData.task.id" class="form-group">
        <label for="status">Status:</label>
        <select v-model="taskData.task.status" class="form-control">
          <option value="pending">Pending</option>
          <option value="completed">Completed</option>
        </select>
      </div>
      <button type="submit" class="btn btn-primary">Add Task</button>
    </form>
    <div v-if="taskData.serverError" class="server-error">
      {{ taskData.serverError }}
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, watch } from "vue";
import { useTaskForm } from "@/composables/useTaskForm";
import { Task } from "@/types/types";

export default defineComponent({
  props: {
    task: {
      type: Object as () => Task | null,
      default: null,
    },
  },
  setup(props, { emit }) {
    const { taskData, submitTaskForm } = useTaskForm();

    watch(
      () => props.task,
      (newTask) => {
        if (newTask) {
          taskData.task = { ...newTask };
        } else {
          taskData.task = {
            title: "",
            description: "",
            dueDate: "",
            status: "pending",
          };
        }
      },
      { immediate: true }
    );

    const submitForm = async () => {
      const isSuccess = await submitTaskForm(taskData.task.id);
      if (isSuccess) {
        emit("task-created");
      }
    };

    return {
      taskData,
      submitForm,
    };
  },
});
</script>
