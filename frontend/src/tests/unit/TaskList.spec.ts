import { shallowMount } from "@vue/test-utils";
import TaskList from "@/components/TaskList.vue";
import { Task } from "@/types/types";

jest.mock("axios");

describe("TaskList.vue", () => {
  const tasks: Task[] = [
    {
      id: 1,
      title: "Task 1",
      description: "Description 1",
      dueDate: "2021-01-01",
      status: "In Progress",
    },
    {
      id: 2,
      title: "Task 2",
      description: "Description 2",
      dueDate: "2021-01-02",
      status: "In Progress",
    },
  ];
  const wrapper = shallowMount(TaskList, {
    props: {
      taskListData: {
        tasks: tasks,
        error: null,
      },
    },
  });

  it("renders a list of tasks", () => {
    const items = wrapper.findAll("h3");
    expect(items).toHaveLength(tasks.length);
  });

  it("emits an event when edit button is clicked", async () => {
    const items = wrapper.findAll("button");
    await items[0].trigger("click");
    expect(wrapper.emitted("edit-task")).toBeTruthy();
  });

  it("emits an event when delte button is clicked", async () => {
    const items = wrapper.findAll("button");
    await items[1].trigger("click");
    expect(wrapper.emitted("delete-task")).toBeTruthy();
  });
});
