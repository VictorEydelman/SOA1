<script setup lang="ts">
import * as v from 'valibot'
import type {FormSubmitEvent} from '@nuxt/ui'

definePageMeta({
  title: 'update'
})

// Valibot схема для валидации
const schema = v.object({
  name: v.pipe(
      v.string(),
      v.minLength(1, 'Имя должно быть'),
  ),
  coordinates: v.object({
    x: v.pipe(
        v.number('должно быть числом')
    ),
    y: v.pipe(
        v.number('должно быть числом')
    )
  }),
  fromId: v.pipe(
      v.number('должно быть числом')
  ),
  toId: v.pipe(
      v.number('должно быть числом')
  ),
  distance: v.optional(
      v.pipe(
          v.number('должно быть числом'),
          v.minValue(2, 'Значение поля должно быть больше 1')
      )
  )
})

type Schema = v.InferOutput<typeof schema>

const state = reactive({
  name: '',
  coordinates: {
    x: 0,
    y: 0
  },
  fromId: undefined as number,
  toId: undefined as number,
  distance: undefined as number | undefined
})

const toast = useToast()

function resetForm() {
  state.name = ''
  state.coordinates = { x: 0, y: 0 }
  state.fromId = undefined
  state.toId = undefined
  state.distance = undefined
}
async function onSubmit(event: FormSubmitEvent<Schema>) {
  try {
    const rd = event.data
    const response = await $fetch(`${SECOND_URL}/route/add/${rd.fromId}/${rd.toId}/${rd.distance}`, {
      method: 'POST',
      query: {
        name: rd.name,
        'coordinates-x': rd.coordinates.x,
        'coordinates-y': rd.coordinates.y
      }
    });
    toast.add({
      title: 'Success',
      description: 'Route created successfully',
      color: 'green'
    })
    resetForm()

  } catch (error: any) {
    toast.add({
      title: 'Error',
      description: `Failed to save route: ${error.message || 'Unknown error'}`,
      color: 'red'
    })
    console.error('Error submitting form:', error)
  }
}

</script>

<template>
  <div class="max-w-2xl mx-auto p-6">
    <div class="flex justify-between items-center mb-6">
      <h1 class="text-2xl font-bold">
        Create New Route Between Locations
      </h1>
    </div>

    <UForm :schema="schema" :state="state" class="space-y-6" @submit="onSubmit">
      <!-- Basic Route Information -->
      <div class="grid grid-cols-1 gap-4">
        <UFormField label="Route Name" name="name">
          <UInput v-model="state.name" placeholder="Enter route name" />
        </UFormField>

        <div class="grid grid-cols-2 gap-4">
          <UFormField label="Distance" name="distance">
            <UInput
                v-model="state.distance"
                type="number"
                placeholder="Optional distance"
            />
          </UFormField>
        </div>
        <div class="grid grid-cols-2 gap-4">
          <UFormField label="From ID" name="fromId">
            <UInput
                v-model="state.fromId"
                type="number"
                placeholder="Location from ID"
            />
          </UFormField>
        </div>
        <div class="grid grid-cols-2 gap-4">
          <UFormField label="To ID" name="toId">
            <UInput
                v-model="state.toId"
                type="number"
                placeholder="Location to ID"
            />
          </UFormField>
        </div>
      </div>

      <!-- Coordinates -->
      <div class="border rounded-lg p-4">
        <h3 class="text-lg font-semibold mb-4">Coordinates</h3>
        <div class="grid grid-cols-2 gap-4">
          <UFormField label="X Coordinate" name="coordinates.x">
            <UInput
                v-model="state.coordinates.x"
                type="number"
                step="0.1"
            />
          </UFormField>
          <UFormField label="Y Coordinate" name="coordinates.y">
            <UInput
                v-model="state.coordinates.y"
                type="number"
                step="0.1"
            />
          </UFormField>
        </div>
      </div>

      <!-- Action Buttons -->
      <div class="flex gap-3 pt-4">
        <UButton type="submit" :loading="false">
          Create Route
        </UButton>

        <UButton
            color="warning"
            variant="soft"
            @click="resetForm"
        >
          Reset Form
        </UButton>
        <NuxtLink to="/">
          <UButton color="error" variant="outline">
            Back to Main
          </UButton>
        </NuxtLink>
      </div>
    </UForm>
  </div>
</template>