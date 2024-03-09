# List of all acceptable commands
## QuickQ

| Command                  | Description               | Arguments                                                   |
|--------------------------|---------------------------|:------------------------------------------------------------|
| /pb/[1..10]              | Set playback fader level  | level: Integer between 0 and 100                            |
| /pb/[1..10]/flash        | Flash playback            | level: Integer                                              |
| /pb/[1..10]/go           | Go on playback            | None                                                        |
| /pb/[1..10]/release      | Release playback          | None                                                        |
| /pb/[1..10]/pause        | Pause playback            | None                                                        |
| /10scene/[1..10]/[1..10] | Control 10Scene buttons   | activate                                                    |
| /feedback/[action]       | Change the feedback level | action: String one of the following: off, pb, exec, pb+exec |

## x32
| Command | Description | Arguments |
|---------|-------------|-----------|

| /config/[type]/[target] | Link to adjacent with each other | type: String one of the following: chlink, auxlink, fxlink, buslink, mtxlink. Target: Integer
| /config/mute/[1..6] | Mute groups | group: Integer between 1 and 6. mute: Boolean |
