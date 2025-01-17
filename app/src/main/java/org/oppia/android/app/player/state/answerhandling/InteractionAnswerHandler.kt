package org.oppia.android.app.player.state.answerhandling

import org.oppia.android.app.model.AnswerErrorCategory
import org.oppia.android.app.model.UserAnswer
import org.oppia.android.app.model.UserAnswerState

/**
 * A handler for interaction answers. Handlers can either require an additional user action before the answer can be
 * processed, or they can push the answer directly to a  [InteractionAnswerReceiver]. Implementations must indicate
 * whether they require an explicit submit button.
 */
interface InteractionAnswerHandler {
  /**
   * Returns whether this handler requires explicit answer submission. Note that this is expected to be an invariant for
   * the lifetime of this handler instance.
   */
  fun isExplicitAnswerSubmissionRequired(): Boolean = true

  /** Returns whether this handler automatically navigates the user to a later state, including answer submission. */
  fun isAutoNavigating(): Boolean = false

  /** Return the current answer's error messages  if not valid else return null. */
  fun checkPendingAnswerError(category: AnswerErrorCategory): String? {
    return null
  }

  /** Return the current answer that is ready for handling. */
  fun getPendingAnswer(): UserAnswer? {
    return null
  }

  /** Returns the current pending answer. */
  fun getUserAnswerState(): UserAnswerState {
    return UserAnswerState.getDefaultInstance()
  }
}

/**
 * A callback that will be called by [InteractionAnswerHandler]s when a user submits an answer. To be implemented by
 * the parent fragment of the handler.
 */
interface InteractionAnswerReceiver {
  fun onAnswerReadyForSubmission(answer: UserAnswer)
}
