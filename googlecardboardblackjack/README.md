# Badjack

Badjack is an experimental Unity blackjack game designed for Google Cardboard. It uses head gestures as the primary input: nod to agree or shake your head to disagree while playing against the dealer.

## Requirements

- Windows with Unity **5.3.4f1**, the project version recorded in `ProjectSettings/ProjectVersion.txt`.
- An Android device with a high-density display.
- A Google Cardboard-style headset.
- The Google Cardboard SDK and other project assets included with the original project.

The project was tested on a Sony Xperia Z3 running Android Lollipop. Compatibility with current Unity, Android, and Google Cardboard versions has not been verified.

## Open in Unity on Windows

1. Open the `googlecardboardblackjack` folder in Unity 5.3.4f1.
2. Open `Assets/Intro.unity` for the start scene or `Assets/BlackJack.unity` for the game scene.
3. Configure the Android build target and device settings.
4. Build and deploy to an Android device.

## How to Play

The game asks yes-or-no questions during setup and play:

- Nod your head to answer yes.
- Shake your head from side to side to answer no.
- Choose whether to bet and whether to take another card.
- Choose to stick to end the current hand.

The game ends when the player or dealer runs out of money, then shows a win or loss scene.

## Source Code

- `Assets/scripts/GameManager.cs` — Blackjack game flow.
- `Assets/scripts/CustomGesture.cs` — Head-gesture interpretation.
- `Assets/scripts/HeadGestures/` — Mobile head tracking and gesture detection.
- `Assets/scripts/Card.cs` and `CardFaceMan.cs` — Card and card-face behavior.
- `Assets/Cardboard/` — Google Cardboard integration.
