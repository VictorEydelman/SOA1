<script setup lang="ts">
import * as v from 'valibot'
import type { FormSubmitEvent } from '@nuxt/ui'

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
  from: v.optional(
      v.object({
        name: v.pipe(
            v.string(),
            v.maxLength(588, 'Длина строки не должна быть больше 588')
        ),
        x: v.pipe(
            v.number('должно быть числом')
        ),
        y: v.pipe(
            v.number('должно быть числом')
        ),
        z: v.pipe(
            v.number('должно быть числом')
        )
      })
  ),
  to: v.object({
    name: v.pipe(
        v.string(),
        v.maxLength(588, 'Длина строки не должна быть больше 588')
    ),
    x: v.pipe(
        v.number('должно быть числом')
    ),
    y: v.pipe(
        v.number('должно быть числом')
    ),
    z: v.pipe(
        v.number('должно быть числом')
    )
  }),
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
  from: undefined as { name: string; x: number; y: number; z: number } | undefined,
  to: {
    name: '',
    x: 0,
    y: 0,
    z: 0
  },
  distance: undefined as number | undefined
})

const isEditing = ref(false)
const routeId = ref<number | null>(null)

const toast = useToast()

function resetForm() {
  isEditing.value = false
  routeId.value = null

  state.name = ''
  state.coordinates = { x: 0, y: 0 }
  state.from = undefined
  state.to = { name: '', x: 0, y: 0, z: 0 }
  state.distance = undefined
}
async function onSubmit(event: FormSubmitEvent<Schema>) {
  try {
    const routeData = event.data

    const xmlData = `<?xml version="1.0" encoding="UTF-8"?>
<route>
	<name>${routeData.name}</name>
	<coordinates>
		<x>${routeData.coordinates.x}</x>
		<y>${routeData.coordinates.y}</y>
	</coordinates>
	${routeData.from ? `
	<from>
		<x>${routeData.from.x}</x>
		<y>${routeData.from.y}</y>
		<z>${routeData.from.z}</z>
		<name>${routeData.from.name}</name>
	</from>
	` : ''}
	<to>
		<x>${routeData.to.x}</x>
		<y>${routeData.to.y}</y>
		<z>${routeData.to.z}</z>
		<name>${routeData.to.name}</name>
	</to>
	${routeData.distance ? `	<distance>${routeData.distance}</distance>` : ''}
</route>`

    const response = await $fetch('http://localhost:5666/api/v1/routes', {
      method: 'POST',
      body: xmlData,
      headers: {
        'accept': 'application/xml',
        'Content-Type': 'application/xml'
      }
    })
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
        Create New Route
      </h1>
      <UButton
          v-if="isEditing"
          color="gray"
          variant="ghost"
          @click="resetForm"
      >
        Create New
      </UButton>
    </div>

    <UForm :schema="schema" :state="state" class="space-y-6" @submit="onSubmit">
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
      </div>

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

      <div class="border rounded-lg p-4">
        <div class="flex justify-between items-center mb-4">
          <h3 class="text-lg font-semibold">From Location</h3>
          <UButton
              v-if="!state.from"
              color="primary"
              variant="outline"
              @click="state.from = { name: '', x: 0, y: 0, z: 0 }"
          >
            Добавить From Location
          </UButton>
          <UButton
              v-else
              color="red"
              variant="ghost"
              @click="state.from = undefined"
          >
            Remove
          </UButton>
        </div>

        <div v-if="state.from" class="grid grid-cols-1 gap-4">
          <UFormField label="From Location Name" name="from.name">
            <UInput v-model="state.from.name" placeholder="Enter from location name" />
          </UFormField>
          <div class="grid grid-cols-3 gap-4">
            <UFormField label="From X" name="from.x">
              <UInput v-model="state.from.x" type="number" step="0.1" />
            </UFormField>
            <UFormField label="From Y" name="from.y">
              <UInput v-model="state.from.y" type="number" step="0.1" />
            </UFormField>
            <UFormField label="From Z" name="from.z">
              <UInput v-model="state.from.z" type="number" step="0.1" />
            </UFormField>
          </div>
        </div>
      </div>

      <div class="border rounded-lg p-4">
        <h3 class="text-lg font-semibold mb-4">To Location</h3>
        <div class="grid grid-cols-1 gap-4">
          <UFormField label="To Location Name" name="to.name">
            <UInput v-model="state.to.name" placeholder="Enter to location name" />
          </UFormField>
          <div class="grid grid-cols-3 gap-4">
            <UFormField label="To X" name="to.x">
              <UInput v-model="state.to.x" type="number" step="0.1" />
            </UFormField>
            <UFormField label="To Y" name="to.y">
              <UInput v-model="state.to.y" type="number" step="0.1" />
            </UFormField>
            <UFormField label="To Z" name="to.z">
              <UInput v-model="state.to.z" type="number" step="0.1" />
            </UFormField>
          </div>
        </div>
      </div>

      <div class="flex gap-3 pt-4">
        <UButton type="submit" :loading="false">
          Create Route
        </UButton>

        <UButton
            color="gray"
            variant="ghost"
            @click="resetForm"
        >
          Cancel
        </UButton>
      </div>
    </UForm>
  </div>
</template>