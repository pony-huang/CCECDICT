package org.github.ponking66.ccecdit.completion;

import com.intellij.patterns.PsiElementPattern;

public interface BaseCompletion {

    PsiElementPattern.Capture<?> inPsiElement();
}
