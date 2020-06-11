declare module "@capacitor/core" {
  interface PluginRegistry {
    CapacitorFileChooser: CapacitorFileChooserPlugin;
  }
}

export interface CapacitorFileChooserPlugin {
  picker(options: { action: string, startDirectory: string }): Promise<{paths: string}>;
}
